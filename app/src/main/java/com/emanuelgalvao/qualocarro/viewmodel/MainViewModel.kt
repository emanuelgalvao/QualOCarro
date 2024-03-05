package com.emanuelgalvao.qualocarro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelgalvao.qualocarro.model.Vehicle
import com.emanuelgalvao.qualocarro.repository.ApiResponse
import com.emanuelgalvao.qualocarro.repository.VehicleRepository
import kotlinx.coroutines.launch

sealed interface MainViewState {
    class Error(val message: String) : MainViewState
    class Success(val data: Vehicle): MainViewState
    object Loading: MainViewState
    object Default: MainViewState
    object OpenInfo: MainViewState
}

sealed interface MainViewIntent {
    class SearchVehicle(val plate: String): MainViewIntent
    object OpenInfoBottomSheet: MainViewIntent
    object CloseBottomSheet: MainViewIntent
}

class MainViewModel(
    private val vehicleRepository: VehicleRepository
): ViewModel() {

    private val CAR_PLATE_REGEX = "[A-z]{3}\\d[A-j0-9]\\d{2}"

    private val _state = MutableLiveData<MainViewState>(MainViewState.Default)
    val state: LiveData<MainViewState> = _state

    fun handleIntent(intent: MainViewIntent) {
        when (intent) {
            MainViewIntent.CloseBottomSheet -> _state.value = MainViewState.Default
            MainViewIntent.OpenInfoBottomSheet -> _state.value = MainViewState.OpenInfo
            is MainViewIntent.SearchVehicle -> findVehicle(intent.plate)
        }
    }

    private fun findVehicle(plate: String) {
        _state.value = MainViewState.Loading
        if (isValidCarPlate(formatPlate(plate))) {
            viewModelScope.launch {
                val response = vehicleRepository.findVehicle(formatPlate(plate))
                when (response) {
                    is ApiResponse.Success -> {
                        _state.value = MainViewState.Success(response.data)
                    }
                    is ApiResponse.Error -> {
                        _state.value = MainViewState.Error(response.message)
                    }
                }
            }
        }
    }

    private fun formatPlate(plate: String): String {
        return plate.replace("-", "")
    }

    private fun isValidCarPlate(plate: String): Boolean {
        if (!Regex(CAR_PLATE_REGEX).matches(plate)) {
            _state.value = MainViewState.Error("Preencha a placa corretamente.")
            return false
        }
        return true
    }
}
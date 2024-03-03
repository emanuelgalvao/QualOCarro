package com.emanuelgalvao.qualocarro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuelgalvao.qualocarro.model.Vehicle
import com.emanuelgalvao.qualocarro.repository.ApiResponse
import com.emanuelgalvao.qualocarro.repository.VehicleRepository
import kotlinx.coroutines.launch

sealed interface MainViewEvent {
    class Error(val message: String) : MainViewEvent
    class Success(val data: Vehicle): MainViewEvent

}

class MainViewModel(
    private val vehicleRepository: VehicleRepository
): ViewModel() {

    private val CAR_PLATE_REGEX = "[A-z]{3}\\d[A-j0-9]\\d{2}"

    private val _event = MutableLiveData<MainViewEvent>()
    val event: LiveData<MainViewEvent> = _event

    fun findVehicle(plate: String) {
        if (isValidCarPlate(plate)) {
            viewModelScope.launch {
                val response = vehicleRepository.findVehicle(plate)
                when (response) {
                    is ApiResponse.Success -> {
                        _event.value = MainViewEvent.Success(response.data)
                    }
                    is ApiResponse.Error -> {
                        _event.value = MainViewEvent.Error(response.message)
                    }
                }
            }
        }
    }

    private fun isValidCarPlate(plate: String): Boolean {
        if (!Regex(CAR_PLATE_REGEX).matches(plate)) {
            _event.value = MainViewEvent.Error("Preencha a placa corretamente.")
            return false
        }
        return true
    }
}
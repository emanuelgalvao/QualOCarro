package com.emanuelgalvao.qualocarro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanuelgalvao.qualocarro.listener.ApiListener
import com.emanuelgalvao.qualocarro.listener.ValidationListener
import com.emanuelgalvao.qualocarro.model.Vehicle
import com.emanuelgalvao.qualocarro.repository.VehicleRepository

class MainViewModel: ViewModel() {

    private val mVehicleRepository = VehicleRepository()

    private val mValidation = MutableLiveData<ValidationListener>()
    val validation: LiveData<ValidationListener> = mValidation

    private val mVehicle = MutableLiveData<Vehicle>()
    val vehicle: LiveData<Vehicle> = mVehicle

    fun findVehicle(board: String) {
        if (validateBoard(board)) {
            mVehicleRepository.findVehicle(board, object : ApiListener {
                override fun onSucess(vehicle: Vehicle) {
                    mValidation.value = ValidationListener()
                    mVehicle.value = vehicle
                }

                override fun onFailure(message: String) {
                    mValidation.value = ValidationListener(message)
                }
            })
        }
    }

    private fun validateBoard(board: String): Boolean {

        var valid = false

        if (board.length == 7){
            if(board[0].isLetter() && board[1].isLetter() && board[2].isLetter() && board[3].isDigit() && board[4].isLetterOrDigit() && board[5].isDigit() && board[6].isDigit()) {
                valid = true
            }
        }

        if (!valid){
            mValidation.value = ValidationListener("Preencha a placa corretamente.")
        }

        return valid
    }
}
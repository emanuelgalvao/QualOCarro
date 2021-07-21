package com.emanuelgalvao.qualocarro.listener

import com.emanuelgalvao.qualocarro.model.Vehicle

interface ApiListener {

    fun onSucess(vehicle: Vehicle)

    fun onFailure(message: String)
}
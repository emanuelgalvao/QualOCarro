package com.emanuelgalvao.qualocarro.repository

import com.emanuelgalvao.qualocarro.listener.ApiListener
import com.emanuelgalvao.qualocarro.model.Vehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleRepository {

    private val mRemote = RetrofitClient.createService(VehicleService::class.java)

    fun findVehicle(board: String, listener: ApiListener) {
        val call: Call<Vehicle> = mRemote.findVehicle(board)
        call.enqueue(object : Callback<Vehicle> {
            override fun onResponse(call: Call<Vehicle>, response: Response<Vehicle>) {
                when {
                    response.code() == 200 -> {
                        response.body()?.let { listener.onSucess(it) }
                    }
                    response.code() == 429 -> {
                        listener.onFailure("Muitas consultas sendo realizadas.\nTente novamente em alguns segundos.")
                    }
                    else -> {
                        listener.onFailure("Ocorreu um erro.\nVerifique a placa digitada e tente novamente.")
                    }
                }
            }

            override fun onFailure(call: Call<Vehicle>, t: Throwable) {
                listener.onFailure("Um erro inesperado ocorreu.\nTente novamente mais tarde.")
            }
        })
    }
}
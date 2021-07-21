package com.emanuelgalvao.qualocarro.repository

import com.emanuelgalvao.qualocarro.model.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleService {

    @GET("{board}/json")
    fun findVehicle(@Path(value = "board", encoded = true) board: String): Call<Vehicle>
}
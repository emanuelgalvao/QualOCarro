package com.emanuelgalvao.qualocarro.repository

import com.emanuelgalvao.qualocarro.model.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleService {

    @GET("{plate}/json")
    suspend fun findVehicle(@Path("plate") plate: String): Response<Vehicle>
}
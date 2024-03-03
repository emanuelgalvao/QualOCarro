package com.emanuelgalvao.qualocarro.repository

interface VehicleRepository {

    suspend fun findVehicle(plate: String): ApiResponse
}
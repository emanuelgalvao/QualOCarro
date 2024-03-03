package com.emanuelgalvao.qualocarro.repository

class VehicleRemoteDataSource(
    private val vehicleService: VehicleService
): VehicleRepository {

    override suspend fun findVehicle(plate: String): ApiResponse {
        val response = vehicleService.findVehicle(plate)
        return if (response.isSuccessful) {
            response.body()?.let {
                ApiResponse.Success(it)
            } ?: ApiResponse.Error("Ocorreu um erro. Verifique a placa digitada e tente novamente.")
        } else if (response.code() == 429) {
            ApiResponse.Error("Muitas consultas sendo realizadas. Tente novamente em alguns segundos.")
        } else {
            ApiResponse.Error("Ocorreu um erro. Verifique a placa digitada e tente novamente.")
        }
    }
}
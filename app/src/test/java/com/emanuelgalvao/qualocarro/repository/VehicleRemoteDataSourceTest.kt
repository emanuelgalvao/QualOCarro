package com.emanuelgalvao.qualocarro.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class VehicleRemoteDataSourceTest {

    private lateinit var vehicleRemoteDataSource: VehicleRemoteDataSource

    @MockK
    private lateinit var vehicleService: VehicleService

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        vehicleRemoteDataSource = VehicleRemoteDataSource((vehicleService))
    }
    
    @Test
    fun `findVehicle method should return ApiResponse Success when request is successful`() = runTest {
        val plate = "ARN-2229"
        coEvery { vehicleService.findVehicle(any()) } returns Response.success(mockk())

        val response = vehicleRemoteDataSource.findVehicle(plate)

        Assert.assertTrue(response is ApiResponse.Success)
    }

    @Test
    fun `findVehicle method should return ApiResponse Error when request response code is 429`() = runTest {
        val plate = "ARN-2229"
        coEvery { vehicleService.findVehicle(plate) } returns
            Response.error(
                429,
                ResponseBody.create(MediaType.parse("application/json"), "")
            )

        val response = vehicleRemoteDataSource.findVehicle(plate)
        val errorMessage = (response as? ApiResponse.Error)?.message

        Assert.assertEquals(
            "Muitas consultas sendo realizadas. Tente novamente em alguns segundos.",
            errorMessage
        )
    }

    @Test
    fun `findVehicle method should return ApiResponse Error when request fails`() = runTest {
        val plate = "ARN-2229"
        coEvery { vehicleService.findVehicle(plate) } returns
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("application/json"), "")
            )

        val response = vehicleRemoteDataSource.findVehicle(plate)
        val errorMessage = (response as? ApiResponse.Error)?.message

        Assert.assertEquals(
            "Ocorreu um erro. Verifique a placa digitada e tente novamente.",
            errorMessage
        )
    }
}
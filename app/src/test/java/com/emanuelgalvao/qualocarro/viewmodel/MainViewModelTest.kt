package com.emanuelgalvao.qualocarro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.emanuelgalvao.qualocarro.repository.ApiResponse
import com.emanuelgalvao.qualocarro.repository.VehicleRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var vehicleRepository: VehicleRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(vehicleRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `findVehicle method should post success event when plate is valid and request is successful`() = runTest {
        val plate = "ARN-2229"
        coEvery { vehicleRepository.findVehicle(any()) } returns ApiResponse.Success(mockk(relaxed = true))

        launch{ viewModel.findVehicle(plate) }
        advanceUntilIdle()

        Assert.assertTrue(viewModel.event.value is MainViewEvent.Success)
    }

    @Test
    fun `findVehicle method should post error event when plate is valid but request fail`() = runTest {
        val plate = "ARN-2229"
        coEvery { vehicleRepository.findVehicle(any()) } returns ApiResponse.Error("")

        launch { viewModel.findVehicle(plate) }
        advanceUntilIdle()

        Assert.assertTrue(viewModel.event.value is MainViewEvent.Error)
    }

    @Test
    fun `findVehicle method should post error event when plate is invalid`() = runTest {
        val plate = "AR2-2229"

        launch { viewModel.findVehicle(plate) }
        advanceUntilIdle()

        val errorMessage = (viewModel.event.value as? MainViewEvent.Error)?.message

        Assert.assertEquals("Preencha a placa corretamente.", errorMessage)
    }
}
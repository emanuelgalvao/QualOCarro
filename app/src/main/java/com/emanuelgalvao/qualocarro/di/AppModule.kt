package com.emanuelgalvao.qualocarro.di

import com.emanuelgalvao.qualocarro.repository.RetrofitClient
import com.emanuelgalvao.qualocarro.repository.VehicleRemoteDataSource
import com.emanuelgalvao.qualocarro.repository.VehicleRepository
import com.emanuelgalvao.qualocarro.repository.VehicleService
import com.emanuelgalvao.qualocarro.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single<VehicleService> { RetrofitClient.getClient().create(VehicleService::class.java) }

    single<VehicleRepository> { VehicleRemoteDataSource(get()) }

    viewModel { MainViewModel(get()) }
}
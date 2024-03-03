package com.emanuelgalvao.qualocarro.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://apicarros.com/v1/consulta/"
    private lateinit var client: Retrofit

    fun getClient(): Retrofit {
        if (!this::client.isInitialized)
            initializeClient()
        return client
    }

    private fun initializeClient() {
        client = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}
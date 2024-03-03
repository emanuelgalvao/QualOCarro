package com.emanuelgalvao.qualocarro.repository

import com.emanuelgalvao.qualocarro.model.Vehicle

sealed interface ApiResponse {
    class Error(val message: String): ApiResponse
    class Success(val data: Vehicle): ApiResponse
}
package io.paulocosta.weathersong.data.remote

sealed class ApiResponse

data class SuccessfulDataApiResponse(
        val statusCode: Int,
        val data: Any): ApiResponse()

data class SuccessfulEmptyResponse(val statusCode: Int): ApiResponse()

data class ErrorApiResponse(val statusCode: Int, val error: String): ApiResponse()
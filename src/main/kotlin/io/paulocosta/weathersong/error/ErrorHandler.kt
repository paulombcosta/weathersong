package io.paulocosta.weathersong.error

import io.paulocosta.weathersong.data.remote.ApiResponse
import io.paulocosta.weathersong.data.remote.ErrorApiResponse
import org.springframework.http.HttpStatus
import retrofit2.HttpException

object ErrorHandler {

    fun handleError(error: Throwable): ApiResponse {
        return if (error is HttpException) {
            ErrorApiResponse(error.code(), error.response().errorBody()?.string() ?: error.message())
        } else {
            ErrorApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error")
        }
    }
}
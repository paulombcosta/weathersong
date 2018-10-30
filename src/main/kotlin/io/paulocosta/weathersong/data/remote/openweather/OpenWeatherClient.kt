package io.paulocosta.weathersong.data.remote.openweather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherClient {

    @GET("weather")
    fun getWeatherInfoByCityName(
            @Query("q") name: String,
            @Query("units") units: String = "metric"): Single<OpenWeatherAPIResponse>

    @GET("weather")
    fun getWeatherInfoByLatLong(
            @Query("lat") lat: Double,
            @Query("lng") lng: Double,
            @Query("units") units: String = "metric"): Single<OpenWeatherAPIResponse>

}
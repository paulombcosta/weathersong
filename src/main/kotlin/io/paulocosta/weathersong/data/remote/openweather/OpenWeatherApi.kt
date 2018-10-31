package io.paulocosta.weathersong.data.remote.openweather

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getWeatherInfoByCityName(
            @Query("q") name: String,
            @Query("units") units: String = "metric"): Single<OpenWeatherAPIResponse>

    @GET("weather")
    fun getWeatherInfoByLatLong(
            @Query("lat") lat: Double,
            @Query("lon") lng: Double,
            @Query("units") units: String = "metric"): Single<OpenWeatherAPIResponse>

}
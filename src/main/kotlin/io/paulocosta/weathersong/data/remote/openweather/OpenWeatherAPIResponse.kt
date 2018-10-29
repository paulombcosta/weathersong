package io.paulocosta.weathersong.data.remote.openweather

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpenWeatherAPIResponse(val main: Main)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Main(val temp: Double)
package io.paulocosta.weathersong.data.remote.openweather

data class OpenWeatherAPIResponse(val main: Main)

data class Main(val temp: Double)
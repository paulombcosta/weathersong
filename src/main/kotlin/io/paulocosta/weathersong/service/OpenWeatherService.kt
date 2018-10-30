package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.model.Temperature
import io.paulocosta.weathersong.data.remote.openweather.OpenWeatherApi
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OpenWeatherService @Autowired constructor(val openWeatherApi: OpenWeatherApi) {

    fun getTemperatureByCityName(cityName: String): Single<Temperature> {
        return openWeatherApi.getWeatherInfoByCityName(cityName)
                .map { Temperature(it.main.temp) }
    }

    fun getTemperatureByLatLng(lat: Double, lng: Double): Single<Temperature> {
        return openWeatherApi.getWeatherInfoByLatLong(lat, lng)
                .map { Temperature(it.main.temp) }
    }

}
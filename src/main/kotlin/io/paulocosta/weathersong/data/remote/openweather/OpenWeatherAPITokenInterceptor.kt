package io.paulocosta.weathersong.data.remote.openweather

import okhttp3.Interceptor
import okhttp3.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OpenWeatherAPITokenInterceptor : Interceptor {

    @Value("\${open.weather.api.key}")
    lateinit var openWeatherApiKey: String

    companion object {
        const val OPEN_WEATHER_API_KEY_PARAM = "APPID"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
                .addQueryParameter(OPEN_WEATHER_API_KEY_PARAM, openWeatherApiKey)
                .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
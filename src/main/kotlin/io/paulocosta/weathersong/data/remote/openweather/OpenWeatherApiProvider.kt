package io.paulocosta.weathersong.data.remote.openweather

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Component
class OpenWeatherApiProvider {

    @Autowired
    lateinit var interceptor: OpenWeatherAPITokenInterceptor

    @Value("\${open.weather.api.endpoint}")
    lateinit var openWeatherApiEndpoint: String

    @Bean
    fun provideRetrofitOpenWeather(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(openWeatherApiEndpoint)
                .client(provideOpenWeatherHTTPClient(interceptor))
                .addConverterFactory(JacksonConverterFactory.create(provideObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Bean
    fun provideOpenWeatherHTTPClient(interceptor: OpenWeatherAPITokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @Bean
    fun provideOpenWeatherClient(): OpenWeatherApi {
        return provideRetrofitOpenWeather().create(OpenWeatherApi::class.java)
    }

    @Bean
    fun provideObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerKotlinModule()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return mapper
    }

}


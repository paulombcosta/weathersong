package io.paulocosta.weathersong.data.remote.openweather

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Component
class OpenWeatherApiProvider {

    @Autowired
    lateinit var interceptor: OpenWeatherAPITokenInterceptor

    @Bean
    fun provideRetrofitOpenWeather(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
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
        return mapper
    }

}


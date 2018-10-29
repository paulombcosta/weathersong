package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Component
class SpotifyAuthClientProvider {

    @Value("\${spotify.auth.endpoint}")
    lateinit var spotifyAuthEndpoint: String

    @Bean
    fun provideRetrofitSpotifyAuth(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(spotifyAuthEndpoint)
                .addConverterFactory(JacksonConverterFactory.create(provideObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Bean
    fun provideObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerKotlinModule()
        return mapper
    }

    @Bean
    fun provideSpotifyAuthClient(): SpotifyAuthClient {
        return provideRetrofitSpotifyAuth().create(SpotifyAuthClient::class.java)
    }


}
package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

@Component
class SpotifyApiProvider {

    @Value("\${spotify.auth.endpoint}")
    lateinit var spotifyAuthEndpoint: String

    @Value("\${spotify.api.endpoint}")
    lateinit var spotifyApiEndpoint: String

    @Bean
    fun provideRetrofitSpotifyAuth(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(spotifyAuthEndpoint)
                .addConverterFactory(JacksonConverterFactory.create(provideObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Bean
    fun provideAPIRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(spotifyApiEndpoint)
                .addConverterFactory(JacksonConverterFactory.create(provideObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Bean
    fun provideObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerKotlinModule()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return mapper
    }

    @Bean
    fun provideSpotifyAuthClient(): SpotifyAuthApi {
        return provideRetrofitSpotifyAuth().create(SpotifyAuthApi::class.java)
    }

    @Bean
    fun providePlaylistsCateogoryClient(): SpotifyPlaylistCategoryApi {
        return provideAPIRetrofit().create(SpotifyPlaylistCategoryApi::class.java)
    }

    @Bean
    fun providePlaylistClient(): SpotifyPlaylistApi {
        return provideAPIRetrofit().create(SpotifyPlaylistApi::class.java)
    }


}
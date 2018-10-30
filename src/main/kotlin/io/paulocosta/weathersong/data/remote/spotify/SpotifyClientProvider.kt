package io.paulocosta.weathersong.data.remote.spotify

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
class SpotifyClientProvider {

    @Value("\${spotify.auth.endpoint}")
    lateinit var spotifyAuthEndpoint: String

    @Value("\${spotify.api.endpoint}")
    lateinit var spotifyApiEndpoint: String

    @Autowired
    lateinit var spotifyApiAuthInterceptor: SpotifyApiAuthInterceptor

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
                .client(provideApiHttpClient())
                .addConverterFactory(JacksonConverterFactory.create(provideObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Bean
    fun provideApiHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(spotifyApiAuthInterceptor)
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

    @Bean
    fun providePlaylistsClient(): SpotifyPlaylistClient {
        return provideAPIRetrofit().create(SpotifyPlaylistClient::class.java)
    }


}
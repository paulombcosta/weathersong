package io.paulocosta.weathersong.data.remote.spotify

import io.paulocosta.weathersong.data.cache.SpotifyAuthTokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpotifyApiAuthInterceptor : Interceptor {

    @Autowired
    lateinit var spotifyAuthTokenRepository: SpotifyAuthTokenRepository

    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
                .addHeader(
                        "Authorization",
                        "Bearer ${spotifyAuthTokenRepository.findById(SpotifyAuthTokenRepository.KEY).get().authToken}")
                .build()
        return chain.proceed(authRequest)
    }

}

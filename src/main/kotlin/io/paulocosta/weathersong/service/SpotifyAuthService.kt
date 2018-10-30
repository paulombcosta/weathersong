package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.persisted.SpotifyAuthToken
import io.paulocosta.weathersong.data.persisted.SpotifyAuthTokenRepository
import io.paulocosta.weathersong.data.remote.ApiResponse
import io.paulocosta.weathersong.data.remote.SuccessfulEmptyResponse
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthAPIResponse
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthClient
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpotifyAuthService @Autowired constructor(
        val spotifyAuthClient: SpotifyAuthClient,
        val spotifyAuthTokenRepository: SpotifyAuthTokenRepository) {

    companion object {
        const val GRANT_CLIENT = "client_credentials"
    }

    @Value("\${spotify.client.id}")
    lateinit var spotifyClientId: String

    @Value("\${spotify.client.secret}")
    lateinit var spotifyClientSecret: String

    fun authenticate(): Single<ApiResponse> {
        val authString = "$spotifyClientId:$spotifyClientSecret"
        val auth = Base64.getEncoder().encodeToString(authString.toByteArray())
        return spotifyAuthClient.authenticate(GRANT_CLIENT, "Basic $auth")
                .flatMap {
                    persistAuthToken(it)
                    Single.just(SuccessfulEmptyResponse(200))
                }
    }

    private fun persistAuthToken(spotifyAuthAPIResponse: SpotifyAuthAPIResponse) {
        spotifyAuthTokenRepository.save(
                SpotifyAuthToken(
                        SpotifyAuthTokenRepository.KEY,
                        spotifyAuthAPIResponse.accessToken
                )
        )
    }

}
package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthAPIResponse
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthClient
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpotifyAuthService @Autowired constructor(val spotifyAuthClient: SpotifyAuthClient) {

    companion object {
        const val GRANT_CLIENT = "grant_client"
    }

    @Value("\${spotify.client.id}")
    lateinit var spotifyClientId: String

    @Value("\${spotify.client.secret}")
    lateinit var spotifyClientSecret: String

    fun authenticate(): Single<SpotifyAuthAPIResponse> {
        val authString = "$spotifyAuthClient:$spotifyClientSecret"
        val auth = Base64.getEncoder().encodeToString(authString.toByteArray())
        return spotifyAuthClient.authenticate(GRANT_CLIENT, "Basic $auth")
    }

}
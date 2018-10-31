package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.cache.SpotifyAuthToken
import io.paulocosta.weathersong.data.cache.SpotifyAuthTokenRepository
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpotifyAuthService @Autowired constructor(
        val spotifyAuthApi: SpotifyAuthApi,
        val spotifyAuthTokenRepository: SpotifyAuthTokenRepository) {

    companion object {
        const val GRANT_CLIENT = "client_credentials"
    }

    @Value("\${spotify.client.id}")
    lateinit var spotifyClientId: String

    @Value("\${spotify.client.secret}")
    lateinit var spotifyClientSecret: String

    val authScheduler = Schedulers.single()

    fun getAuthToken(): Single<SpotifyAuthToken> {
        return retrieveAuthToken()
                .flatMap {
                    if (it.isPresent) {
                        Single.just(it.get())
                    } else {
                        authenticate()
                    }
                }
    }

    fun authenticate(): Single<SpotifyAuthToken> {
        val authString = "$spotifyClientId:$spotifyClientSecret"
        val auth = Base64.getEncoder().encodeToString(authString.toByteArray())
        return spotifyAuthApi.authenticate(GRANT_CLIENT, "Basic $auth")
                .subscribeOn(authScheduler)
                .map { SpotifyAuthToken(SpotifyAuthTokenRepository.KEY, it.accessToken) }
                .doAfterSuccess(this::persistAuthToken)
    }

    private fun persistAuthToken(spotifyAuthToken: SpotifyAuthToken) {
        spotifyAuthTokenRepository.save(spotifyAuthToken)
    }

    private fun retrieveAuthToken(): Single<Optional<SpotifyAuthToken>> {
        return Single.just(spotifyAuthTokenRepository.findById(SpotifyAuthTokenRepository.KEY))
    }

}
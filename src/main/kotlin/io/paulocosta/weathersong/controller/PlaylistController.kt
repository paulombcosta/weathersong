package io.paulocosta.weathersong.controller

import io.paulocosta.weathersong.data.persisted.SpotifyAuthToken
import io.paulocosta.weathersong.data.persisted.SpotifyAuthTokenRepository
import io.paulocosta.weathersong.data.remote.openweather.OpenWeatherAPIResponse
import io.paulocosta.weathersong.data.remote.openweather.OpenWeatherClient
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthAPIResponse
import io.paulocosta.weathersong.data.remote.spotify.SpotifyAuthClient
import io.paulocosta.weathersong.service.SpotifyAuthService
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistController @Autowired constructor(
        val openWeatherClient: OpenWeatherClient,
        val spotifyAuthService: SpotifyAuthService,
        val spotifyAuthTokenRepository: SpotifyAuthTokenRepository) {

    @GetMapping("/playlist")
    fun hello(): Single<OpenWeatherAPIResponse> {
        return openWeatherClient.getWeatherInfoByCityName("London")
    }

    @GetMapping("/auth")
    fun auth(): Single<SpotifyAuthAPIResponse> {
        return spotifyAuthService.authenticate()
    }

    @GetMapping("/persistence")
    fun persistenceTest(): Single<SpotifyAuthToken> {
        spotifyAuthTokenRepository.findById(SpotifyAuthTokenRepository.KEY)
    }

}
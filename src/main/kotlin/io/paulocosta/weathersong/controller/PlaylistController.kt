package io.paulocosta.weathersong.controller

import io.paulocosta.weathersong.data.persisted.SpotifyAuthTokenRepository
import io.paulocosta.weathersong.data.remote.ApiResponse
import io.paulocosta.weathersong.data.remote.SuccessfulDataApiResponse
import io.paulocosta.weathersong.data.remote.openweather.OpenWeatherAPIResponse
import io.paulocosta.weathersong.data.remote.openweather.OpenWeatherApi
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistApi
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistCategoryApi
import io.paulocosta.weathersong.service.SpotifyAuthService
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistController @Autowired constructor(
        val openWeatherApi: OpenWeatherApi,
        val spotifyAuthService: SpotifyAuthService,
        val spotifyAuthTokenRepository: SpotifyAuthTokenRepository,
        val spotifyPlaylistCategoryApi: SpotifyPlaylistCategoryApi,
        val spotifyPlaylistApi: SpotifyPlaylistApi) {

    @GetMapping("/weather")
    fun weather(): Single<OpenWeatherAPIResponse> {
        return openWeatherApi.getWeatherInfoByCityName("London")
    }

    @GetMapping("/auth")
    fun auth(): Single<ApiResponse> {
        return spotifyAuthService.authenticate()
    }

    @GetMapping("/persistence")
    fun persistenceTest(): Single<ApiResponse> {
        return Single.just(spotifyAuthTokenRepository.findById(SpotifyAuthTokenRepository.KEY))
                .map { it.get() }
                .map { SuccessfulDataApiResponse(statusCode = 200, data = it) }
    }

    @GetMapping("/playlists")
    fun playlists(): Single<ApiResponse> {
        return spotifyPlaylistCategoryApi.getPlaylists("classical")
                .map {
                    SuccessfulDataApiResponse(statusCode = 200, data = it)
                }
    }

    @GetMapping("/playlist")
    fun playlist(): Single<ApiResponse> {
        return spotifyPlaylistApi.getPlaylist("37i9dQZF1DWWEJlAGA9gs0")
                .map {
                    SuccessfulDataApiResponse(statusCode = 200, data = it)
                }
    }

}
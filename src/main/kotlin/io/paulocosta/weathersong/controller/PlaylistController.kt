package io.paulocosta.weathersong.controller

import io.paulocosta.weathersong.data.remote.ApiResponse
import io.paulocosta.weathersong.error.ErrorHandler
import io.paulocosta.weathersong.service.SpotifyWeatherPlaylistService
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaylistController @Autowired constructor(val spotifyWeatherPlaylistService: SpotifyWeatherPlaylistService) {

    @GetMapping("/playlist/city/{city_name}")
    fun getPlaylistByCityName(@PathVariable("city_name") cityName: String): Single<ApiResponse> {
        return spotifyWeatherPlaylistService.getPlaylistByCityName(cityName)
                .onErrorReturn {ErrorHandler.handleError(it)}
    }

    @GetMapping("/playlist/lat/{lat}/lng/{lng}")
    fun getPlaylistByLatLng(@PathVariable("lat") lat: Double, @PathVariable("lng") lng: Double): Single<ApiResponse> {
        return spotifyWeatherPlaylistService.getPlaylistByLatLng(lat, lng)
                .onErrorReturn {ErrorHandler.handleError(it)}
    }

}
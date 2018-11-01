package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.remote.ApiResponse
import io.paulocosta.weathersong.data.remote.SuccessfulDataApiResponse
import io.paulocosta.weathersong.playlist.PlaylistResolver
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyWeatherPlaylistService @Autowired constructor(
        private val openWeatherService: OpenWeatherService,
        private val playlistResolver: PlaylistResolver,
        private val spotifyPlaylistCategoryService: SpotifyPlaylistCategoryService,
        private val spotifyPlaylistTracksService: SpotifyPlaylistTracksService
) {

    fun getPlaylistByCityName(cityName: String): Single<ApiResponse> {
        return openWeatherService.getTemperatureByCityName(cityName)
                .map { playlistResolver.resolvePlaylist(it.degrees) }
                .flatMap { spotifyPlaylistCategoryService.getPlaylistByCategory(it) }
                .flatMap { spotifyPlaylistTracksService.getPlaylistTracks(it.id) }
                .map { SuccessfulDataApiResponse(200, it) }
    }

    fun getPlaylistByLatLng(lat: Double, lng: Double): Single<ApiResponse> {
        return openWeatherService.getTemperatureByLatLng(lat, lng)
                .map { playlistResolver.resolvePlaylist(it.degrees) }
                .flatMap { spotifyPlaylistCategoryService.getPlaylistByCategory(it) }
                .flatMap { spotifyPlaylistTracksService.getPlaylistTracks(it.id) }
                .map { SuccessfulDataApiResponse(200, it) }
    }

}
package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.model.Playlist
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

    fun getPlaylistByCityName(cityName: String): Single<Playlist> {
        return openWeatherService.getTemperatureByCityName(cityName)
                .map { playlistResolver.resolvePlaylist(it.degrees) }
                .flatMap { spotifyPlaylistCategoryService.getPlaylistByCategory(it) }
                .flatMap { spotifyPlaylistTracksService.getPlaylistTracks(it.id) }
    }

    fun getPlaylistByLatLng(lat: Double, lng: Double): Single<Playlist> {
        return openWeatherService.getTemperatureByLatLng(lat, lng)
                .map { playlistResolver.resolvePlaylist(it.degrees) }
                .flatMap { spotifyPlaylistCategoryService.getPlaylistByCategory(it) }
                .flatMap { spotifyPlaylistTracksService.getPlaylistTracks(it.id) }
    }

}
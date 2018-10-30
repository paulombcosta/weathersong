package io.paulocosta.weathersong.service

import org.springframework.beans.factory.annotation.Autowired

class SpotifyWeatherPlaylistService @Autowired constructor(
        val openWeatherService: OpenWeatherService,
        val spotifyPlaylistCategoryService: SpotifyPlaylistCategoryService,
        val spotifyPlaylistTracksService: SpotifyPlaylistTracksService
) {



}
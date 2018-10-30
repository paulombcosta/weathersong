package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TracksResponse(val items: List<Item>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Item(val item: TrackResponse)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TrackResponse(val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SpotifyPlaylistApiResponse(val tracks: TracksResponse)

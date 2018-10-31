package io.paulocosta.weathersong.data.remote.spotify

data class TracksResponse(val items: List<Item>)

data class Item(val track: TrackResponse)

data class TrackResponse(val name: String)

data class SpotifyPlaylistApiResponse(val tracks: TracksResponse)

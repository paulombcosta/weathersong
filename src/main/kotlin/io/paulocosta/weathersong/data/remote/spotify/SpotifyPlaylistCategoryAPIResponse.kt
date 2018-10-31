package io.paulocosta.weathersong.data.remote.spotify

data class PlaylistsArray(val items: List<PlaylistInfo>)

data class PlaylistInfo(val id: String)

data class SpotifyPlaylistCategoryAPIResponse(val playlists: PlaylistsArray)

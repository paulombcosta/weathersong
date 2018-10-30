package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistsArray(val items: List<PlaylistInfo>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlaylistInfo(val id: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SpotifyPlaylistCategoryAPIResponse(val playlists: PlaylistsArray)

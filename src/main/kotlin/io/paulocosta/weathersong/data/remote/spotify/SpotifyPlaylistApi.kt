package io.paulocosta.weathersong.data.remote.spotify

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpotifyPlaylistApi {

    @GET("/v1/playlists/{playlist_id}")
    fun getPlaylist(
            @Path("playlist_id") playlistId: String,
            @Header("Authorization") authToken: String): Single<SpotifyPlaylistApiResponse>

}
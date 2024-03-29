package io.paulocosta.weathersong.data.remote.spotify

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpotifyPlaylistCategoryApi {

    @GET("v1/browse/categories/{category_id}/playlists")
    fun getPlaylists(
            @Path("category_id") categoryId: String,
            @Header("Authorization") authToken: String
    ): Single<SpotifyPlaylistCategoryAPIResponse>

}
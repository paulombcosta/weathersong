package io.paulocosta.weathersong.data.remote.spotify

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SpotifyPlaylistClient {

    @GET("v1/browse/categories/{category_id}/playlists")
    fun getPlaylists(@Path("category_id") categoryId: String): Single<SpotifyPlaylistCategoryAPIResponse>

}
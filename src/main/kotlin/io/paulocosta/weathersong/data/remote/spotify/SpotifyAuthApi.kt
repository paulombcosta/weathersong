package io.paulocosta.weathersong.data.remote.spotify

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface SpotifyAuthApi {

    @POST("/api/token")
    @FormUrlEncoded
    fun authenticate(@Field("grant_type") grantType: String,
                     @Header("Authorization") authorization: String) : Single<SpotifyAuthAPIResponse>

}
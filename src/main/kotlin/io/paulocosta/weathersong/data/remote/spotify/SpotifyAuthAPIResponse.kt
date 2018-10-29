package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.annotation.JsonProperty

data class SpotifyAuthAPIResponse(
        @JsonProperty("access_token") val accessToken: String,
        @JsonProperty("access_token") val tokenType: String,
        @JsonProperty("access_token") val expiresIn: Double
)

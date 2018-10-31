package io.paulocosta.weathersong.data.remote.spotify

import com.fasterxml.jackson.annotation.JsonProperty

data class SpotifyAuthAPIResponse(
        @JsonProperty("access_token") val accessToken: String,
        @JsonProperty("token_type") val tokenType: String,
        @JsonProperty("expires_in") val expiresIn: Double
)

package io.paulocosta.weathersong.data.cache

import org.springframework.data.annotation.Id
import org.springframework.data.keyvalue.annotation.KeySpace

@KeySpace("authToken")
data class SpotifyAuthToken(@Id val key: String, val authToken: String)
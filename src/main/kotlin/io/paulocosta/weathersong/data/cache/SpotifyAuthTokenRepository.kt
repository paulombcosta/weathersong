package io.paulocosta.weathersong.data.cache

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SpotifyAuthTokenRepository : CrudRepository<SpotifyAuthToken, String> {

    companion object {
        const val KEY = "spotify_auth_token"
    }

}

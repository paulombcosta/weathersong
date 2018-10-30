package io.paulocosta.weathersong.playlist

import io.paulocosta.weathersong.data.model.*
import org.springframework.stereotype.Component

@Component
class PlaylistResolver {

    fun resolvePlaylist(temperature: Int): PlaylistCategory {
        return when {
            temperature > 30 -> PartyPlaylist()
            temperature in 16..29 -> PopPlaylist()
            temperature in 11..13 -> RockPlaylist()
            else -> ClassicalPlaylist()
        }
    }

}


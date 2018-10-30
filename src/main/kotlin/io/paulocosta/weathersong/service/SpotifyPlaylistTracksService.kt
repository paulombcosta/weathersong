package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.model.Playlist
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistApi
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyPlaylistTracksService @Autowired constructor(
        val spotifyPlaylistApi: SpotifyPlaylistApi
) {

    fun getPlaylistTracks(playlistId: String): Single<Playlist> {

    }

}
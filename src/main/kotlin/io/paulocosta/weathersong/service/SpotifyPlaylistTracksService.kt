package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.cache.SpotifyAuthTokenRepository
import io.paulocosta.weathersong.data.model.Playlist
import io.paulocosta.weathersong.data.model.Track
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistApi
import io.paulocosta.weathersong.error.ErrorHandler
import io.reactivex.Flowable
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyPlaylistTracksService @Autowired constructor(
        val spotifyAuthService: SpotifyAuthService,
        val spotifyPlaylistApi: SpotifyPlaylistApi,
        val spotifyAuthTokenRepository: SpotifyAuthTokenRepository
) {

    fun getPlaylistTracks(playlistId: String): Single<Playlist> {
        return spotifyAuthService.getAuthToken()
                .flatMap { spotifyPlaylistApi.getPlaylist(playlistId, "Bearer ${it.authToken}")}
                .map { it.tracks.items }
                .toObservable()
                .flatMapIterable { it }
                .map { Track(it.track.name) }
                .toList()
                .map { Playlist(id = "", tracks = it) }
                .retryWhen {
                    it.flatMap {
                        if (ErrorHandler.isUnauthorized(it)) {
                            spotifyAuthTokenRepository.deleteAll()
                            Flowable.just(true)
                        } else {
                            Flowable.error(it)
                        }
                    }
                }
    }

}
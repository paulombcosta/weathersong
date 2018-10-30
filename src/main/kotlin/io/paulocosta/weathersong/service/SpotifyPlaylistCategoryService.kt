package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.model.Playlist
import io.paulocosta.weathersong.data.model.PlaylistCategory
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistCategoryApi
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyPlaylistCategoryService @Autowired constructor(
        val spotifyPlaylistCategoryApi: SpotifyPlaylistCategoryApi
) {

    fun getPlaylistsByCategory(playlistCategory: PlaylistCategory): Single<List<Playlist>> {
        return spotifyPlaylistCategoryApi
                .getPlaylists(playlistCategory.category)
                .map { it.playlists.items }
                .toObservable()
                .flatMapIterable { it }
                .map { Playlist(id = it.id, tracks = emptyList()) }
                .toList()
    }

}


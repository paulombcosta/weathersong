package io.paulocosta.weathersong.service

import io.paulocosta.weathersong.data.model.Playlist
import io.paulocosta.weathersong.data.model.PlaylistCategory
import io.paulocosta.weathersong.data.remote.spotify.SpotifyPlaylistCategoryApi
import io.reactivex.Single
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SpotifyPlaylistCategoryService @Autowired constructor(
        val spotifyPlaylistCategoryApi: SpotifyPlaylistCategoryApi,
        val spotifyAuthService: SpotifyAuthService
) {

    fun getPlaylistByCategory(playlistCategory: PlaylistCategory): Single<Playlist> {
        return getRandomPlaylistByCategory(playlistCategory)
    }

    private fun getRandomPlaylistByCategory(playlistCategory: PlaylistCategory): Single<Playlist> {
        return spotifyAuthService.getAuthToken()
                .flatMap { spotifyPlaylistCategoryApi
                        .getPlaylists(playlistCategory.category, "Bearer ${it.authToken}") }
                .map { it.playlists.items[Random().nextInt(it.playlists.items.size)] }
                .map { Playlist(id = it.id, tracks = emptyList()) }
    }

}


package io.paulocosta.weathersong.data.model

sealed class PlaylistCategory(val category: String)

class PartyPlaylist: PlaylistCategory("party")

class RockPlaylist: PlaylistCategory("rock")

class PopPlaylist: PlaylistCategory("pop")

class ClassicalPlaylist: PlaylistCategory("classical")

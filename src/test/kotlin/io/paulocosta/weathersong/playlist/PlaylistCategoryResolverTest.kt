package io.paulocosta.weathersong.playlist

import io.paulocosta.weathersong.data.model.ClassicalPlaylist
import io.paulocosta.weathersong.data.model.PartyPlaylist
import io.paulocosta.weathersong.data.model.PopPlaylist
import io.paulocosta.weathersong.data.model.RockPlaylist
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class PlaylistCategoryResolverTest {

    @Spy
    lateinit var playlistResolver: PlaylistResolver

    @Test
    fun `test playlist resolver should return a party playlist if temperature is above 30 degrees celcius`() {
        assertThat(playlistResolver.resolvePlaylist(31.0)).isInstanceOf(PartyPlaylist::class.java)
    }

    @Test
    fun `test playlist resolver should return a pop playlist if temperature is between 15 and 30 degrees celcius`() {
        assertThat(playlistResolver.resolvePlaylist(16.0)).isInstanceOf(PopPlaylist::class.java)
    }

    @Test
    fun `test playlist resolver should return a rock playlist if temperature is between 10 and 14 degrees celcius`() {
        assertThat(playlistResolver.resolvePlaylist(11.0)).isInstanceOf(RockPlaylist::class.java)
    }

    @Test
    fun `test playlist resolver should return a classical playlist if temperature is below 10 degrees celcius`() {
        assertThat(playlistResolver.resolvePlaylist(9.0)).isInstanceOf(ClassicalPlaylist::class.java)
    }

}
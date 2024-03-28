package com.nimesh.vasani.melodybeatblastkmp.network

import com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist.FeaturedPlayList
import com.nimesh.vasani.melodybeatblastkmp.network.models.newreleases.NewReleasedAlbums
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.TopFiftyCharts



interface SpotifyApi {
    suspend fun getTopFiftyChart(): TopFiftyCharts
    suspend fun getNewReleases(): NewReleasedAlbums
    suspend fun getFeaturedPlaylist(): FeaturedPlayList
    suspend fun getPlayList(playlistId: String): TopFiftyCharts

}
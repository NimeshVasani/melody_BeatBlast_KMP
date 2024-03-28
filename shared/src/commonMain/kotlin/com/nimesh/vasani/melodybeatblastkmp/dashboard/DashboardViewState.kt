package com.nimesh.vasani.melodybeatblastkmp.dashboard

import com.nimesh.vasani.melodybeatblastkmp.network.models.featuredplaylist.FeaturedPlayList
import com.nimesh.vasani.melodybeatblastkmp.network.models.newreleases.NewReleasedAlbums
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.TopFiftyCharts


/**
 * Created by abdulbasit on 26/02/2023.
 */
sealed interface DashboardViewState {
    data object Loading : DashboardViewState
    data class Success(
        val topFiftyCharts: TopFiftyCharts,
        val newReleasedAlbums: NewReleasedAlbums,
        val featuredPlayList: FeaturedPlayList
    ) : DashboardViewState

    data class Failure(val error: String) : DashboardViewState
}

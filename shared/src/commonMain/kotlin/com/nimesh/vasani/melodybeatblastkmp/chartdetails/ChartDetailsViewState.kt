package com.nimesh.vasani.melodybeatblastkmp.chartdetails

import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.TopFiftyCharts


/**
 * Created by abdulbasit on 26/02/2023.
 */
sealed interface ChartDetailsViewState {
    data object Loading : ChartDetailsViewState
    data class Success(
        val chartDetails: TopFiftyCharts,
        val playingTrackId: String,
    ) : ChartDetailsViewState

    data class Failure(val error: String) : ChartDetailsViewState
}

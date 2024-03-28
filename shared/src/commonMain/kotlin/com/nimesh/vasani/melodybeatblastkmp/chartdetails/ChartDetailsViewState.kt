package com.nimesh.vasani.melodybeatblastkmp.chartdetails

import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.TopFiftyCharts


sealed interface ChartDetailsViewState {
    data object Loading : ChartDetailsViewState
    data class Success(
        val chartDetails: TopFiftyCharts,
        val playingTrackId: String,
    ) : ChartDetailsViewState

    data class Failure(val error: String) : ChartDetailsViewState
}

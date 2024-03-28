package com.nimesh.vasani.melodybeatblastkmp.decompose

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.nimesh.vasani.melodybeatblastkmp.chartdetails.ChartDetailsViewModel
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.Item




interface ChartDetailsComponent {
    val viewModel: ChartDetailsViewModel
    fun onOutPut(output: Output)
    sealed class Output {
        data object GoBack : Output()
        data class OnPlayAllSelected(val playlist: List<Item>) : Output()
        data class OnTrackSelected(val trackId: String) : Output()
    }

    @Parcelize
    sealed interface Input : Parcelable {

        @Parcelize
        data class TrackUpdated(val trackId: String) : Input, Parcelable
    }
}

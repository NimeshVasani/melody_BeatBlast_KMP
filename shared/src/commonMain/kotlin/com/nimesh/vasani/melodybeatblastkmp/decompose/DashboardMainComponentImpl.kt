package com.nimesh.vasani.melodybeatblastkmp.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nimesh.vasani.melodybeatblastkmp.dashboard.DashboardViewModel
import com.nimesh.vasani.melodybeatblastkmp.network.SpotifyApi



class DashboardMainComponentImpl(
    componentContext: ComponentContext,
    val output: (DashboardMainComponent.Output) -> Unit,
    val spotifyApi: SpotifyApi
) : DashboardMainComponent, ComponentContext by componentContext {
    override val viewModel: DashboardViewModel
        get() = instanceKeeper.getOrCreate { DashboardViewModel(spotifyApi) }

    override fun onOutPut(output: DashboardMainComponent.Output) {
        output(output)
    }
}
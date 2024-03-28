package com.nimesh.vasani.melodybeatblastkmp.decompose

import com.arkivanov.decompose.router.overlay.ChildOverlay
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.Item


interface MusicRoot {

    val childStack: Value<ChildStack<*, Child>>
    val dialogOverlay: Value<ChildOverlay<*, PlayerComponent>>
    fun navigateToPlayerScreen()
    sealed class Child {
        data class Dashboard(val dashboardMainComponent: DashboardMainComponent) : Child()
        data class Details(val detailsComponent: ChartDetailsComponent) : Child()

       data class PlayerScreen(val playerComponent: PlayerComponent) : Child()
    }
}

package com.nimesh.vasani.melodybeatblastkmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.nimesh.vasani.melodybeatblastkmp.chartdetails.ChartDetailsScreen
import com.nimesh.vasani.melodybeatblastkmp.chartdetails.ChartDetailsScreenLarge
import com.nimesh.vasani.melodybeatblastkmp.dashboard.DashboardScreen
import com.nimesh.vasani.melodybeatblastkmp.dashboard.DashboardScreenLarge
import com.nimesh.vasani.melodybeatblastkmp.decompose.MusicRoot
import com.nimesh.vasani.melodybeatblastkmp.decompose.MusicRootImpl
import com.nimesh.vasani.melodybeatblastkmp.player.PlayerScreen
import com.nimesh.vasani.melodybeatblastkmp.playerview.PlayerView

@Composable
internal fun MainCommon(
    rootComponent: MusicRoot,
    isLargeScreen: Boolean
) {
    val dialogOverlay by rootComponent.dialogOverlay.subscribeAsState()

    MyApplicationTheme {

        Box(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxSize()) {

                Children(
                    stack = rootComponent.childStack
                ) {
                    when (val child = it.instance) {
                        is MusicRoot.Child.Dashboard -> {
                            if (isLargeScreen)
                                DashboardScreenLarge(child.dashboardMainComponent)
                            else
                                DashboardScreen(child.dashboardMainComponent)
                        }

                        is MusicRoot.Child.Details -> {
                            if (isLargeScreen)
                                ChartDetailsScreenLarge(child.detailsComponent)
                            else
                                ChartDetailsScreen(child.detailsComponent)
                        }

                        is MusicRoot.Child.PlayerScreen ->{
                        }
                    }
                }
            }
            Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                dialogOverlay.overlay?.instance?.also {
                    PlayerView(it, onclick = {

                        rootComponent.navigateToPlayerScreen()
                    })
                }
            }
        }
    }
}

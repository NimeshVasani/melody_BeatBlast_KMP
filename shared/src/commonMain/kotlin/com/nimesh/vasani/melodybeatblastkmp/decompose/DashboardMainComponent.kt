package com.nimesh.vasani.melodybeatblastkmp.decompose

import com.nimesh.vasani.melodybeatblastkmp.dashboard.DashboardViewModel



interface DashboardMainComponent {
    val viewModel: DashboardViewModel

    fun onOutPut(output: Output)

    sealed class Output {
        data class PlaylistSelected(val playlistId: String) : Output()
    }
}

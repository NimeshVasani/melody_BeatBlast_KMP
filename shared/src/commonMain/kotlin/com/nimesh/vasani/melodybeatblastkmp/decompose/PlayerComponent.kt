package com.nimesh.vasani.melodybeatblastkmp.decompose

import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.Item
import com.nimesh.vasani.melodybeatblastkmp.playerview.PlayerViewModel



interface PlayerComponent {
    val viewModel: PlayerViewModel

    fun onOutPut(output: Output)

    sealed class Output {
        data object OnPause : Output()
        data object OnPlay : Output()
        data class OnTrackUpdated(val trackId: String) : Output()
    }

    sealed interface Input {
        data class PlayTrack(val trackId: String) : Input
        data class UpdateTracks(val tracksList: List<Item>) : Input
    }

}

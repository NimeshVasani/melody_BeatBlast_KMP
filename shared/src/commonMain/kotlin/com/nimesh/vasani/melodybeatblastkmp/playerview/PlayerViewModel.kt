package com.nimesh.vasani.melodybeatblastkmp.playerview

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.nimesh.vasani.melodybeatblastkmp.decompose.PlayerComponent
import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.Item
import com.nimesh.vasani.melodybeatblastkmp.player.MediaPlayerController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class PlayerViewModel(
    mediaPlayerController: MediaPlayerController,
    trackList: List<Item>,
    playerInputs: SharedFlow<PlayerComponent.Input>
) : InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    val chartDetailsViewState = MutableStateFlow(PlayerViewState(trackList = trackList, mediaPlayerController = mediaPlayerController))

    init {
        viewModelScope.launch {
            playerInputs.collectLatest {
                when (it) {
                    is PlayerComponent.Input.PlayTrack ->
                        chartDetailsViewState.value = chartDetailsViewState.value.copy(playingTrackId = it.trackId)

                    is PlayerComponent.Input.UpdateTracks ->
                        chartDetailsViewState.value = chartDetailsViewState.value.copy(trackList = it.tracksList)
                }
            }
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}
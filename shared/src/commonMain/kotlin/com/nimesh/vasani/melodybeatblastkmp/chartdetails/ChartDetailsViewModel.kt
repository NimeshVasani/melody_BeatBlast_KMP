package com.nimesh.vasani.melodybeatblastkmp.chartdetails

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.nimesh.vasani.melodybeatblastkmp.decompose.ChartDetailsComponent
import com.nimesh.vasani.melodybeatblastkmp.network.SpotifyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



class ChartDetailsViewModel(
    api: SpotifyApi,
    playlistId: String,
    playingTrackId: String,
    chatDetailsInput: SharedFlow<ChartDetailsComponent.Input>
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    val chartDetailsViewState = MutableStateFlow<ChartDetailsViewState>(ChartDetailsViewState.Loading)

    init {
        viewModelScope.launch {
            launch {
                try {
                    val playlist = api.getPlayList(playlistId)
                    chartDetailsViewState.value = ChartDetailsViewState.Success(
                        chartDetails = playlist,
                        playingTrackId = playingTrackId
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    chartDetailsViewState.value =
                        ChartDetailsViewState.Failure(e.message.toString())
                }
            }
            launch {
                chatDetailsInput.collectLatest {
                    when (it) {
                        is ChartDetailsComponent.Input.TrackUpdated ->
                            when (val state = chartDetailsViewState.value) {
                                is ChartDetailsViewState.Success -> {
                                    chartDetailsViewState.emit(state.copy(playingTrackId = it.trackId))
                                }

                                else -> {}
                            }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}
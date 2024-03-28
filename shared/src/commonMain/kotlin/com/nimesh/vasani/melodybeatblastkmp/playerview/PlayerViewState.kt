package com.nimesh.vasani.melodybeatblastkmp.playerview

import com.nimesh.vasani.melodybeatblastkmp.network.models.topfiftycharts.Item
import com.nimesh.vasani.melodybeatblastkmp.player.MediaPlayerController


/**
 * Created by abdulbasit on 09/04/2023.
 */
data class PlayerViewState(
    val trackList: List<Item>,
    val mediaPlayerController: MediaPlayerController,
    val playingTrackId: String = ""
)


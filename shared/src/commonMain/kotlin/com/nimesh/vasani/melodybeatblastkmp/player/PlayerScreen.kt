package com.nimesh.vasani.melodybeatblastkmp.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.nimesh.vasani.melodybeatblastkmp.decompose.PlayerComponent
import com.nimesh.vasani.melodybeatblastkmp.playerview.playTrack
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun PlayerScreen(playerComponent: PlayerComponent) {
    // Retrieve state from ViewModel
    val state = playerComponent.viewModel.chartDetailsViewState.collectAsState()
    val mediaPlayerController = state.value.mediaPlayerController
    val selectedTrackPlaying = state.value.playingTrackId
    val trackList = state.value.trackList

    val selectedIndex = remember { mutableStateOf(0) }
    var playIcon by remember { mutableStateOf(if (mediaPlayerController.isPlaying()) "⏸" else "▶") }

    val isLoading = remember { mutableStateOf(true) }
    val selectedTrack = trackList.getOrNull(selectedIndex.value)

    // Trigger playTrack effect
    LaunchedEffect(selectedTrack) {
        if (selectedTrack != null) {
            playTrack(selectedTrack, mediaPlayerController, isLoading, selectedIndex, trackList)
        }
    }

    // Update playIcon based on player state
    LaunchedEffect(mediaPlayerController.isPlaying()) {
        playIcon = if (mediaPlayerController.isPlaying()) "⏸" else "▶"
    }

    val progressState = remember { mutableStateOf(0.0f) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF101010))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            // Back button (Replace with your implementation)
            Text(
                text = "Back",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.clickable {
                    // Handle back button click
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Center image of music
            selectedTrack?.let {
                val painter = rememberAsyncImagePainter(it.track?.album?.images?.firstOrNull()?.url.orEmpty())
                Image(
                    painter = painter,
                    contentDescription = "Album Cover",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Track name and artist
            Text(
                text = selectedTrack?.track?.name.orEmpty(),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(
                text = selectedTrack?.track?.artists?.map { it.name }?.joinToString(", ").orEmpty(),
                style = MaterialTheme.typography.body1,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Seekbar
            SeekBar(
                progress = progressState.value,
                onProgressChanged = { progress ->
                    progressState.value = progress
//                    mediaPlayerController.seekTo(progress.toLong())
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pause/play button
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "⏮",
                    color = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            // Handle previous button click
                        }
                )
                Text(
                    text = playIcon,
                    color = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            if (mediaPlayerController.isPlaying()) {
                                playIcon = "▶"
                                mediaPlayerController.pause()
                            } else {
                                playIcon = "⏸"
                                mediaPlayerController.start()
                            }
                        }
                )
                Text(
                    text = "⏭",
                    color = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            // Handle next button click
                        }
                )
            }
        }
    }
}

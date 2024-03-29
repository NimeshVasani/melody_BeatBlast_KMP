package com.nimesh.vasani.melodybeatblastkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.nimesh.vasani.melodybeatblastkmp.MainAndroid
import com.nimesh.vasani.melodybeatblastkmp.decompose.MusicRootImpl
import com.nimesh.vasani.melodybeatblastkmp.network.SpotifyApiImpl
import com.nimesh.vasani.melodybeatblastkmp.player.MediaPlayerController
import com.nimesh.vasani.melodybeatblastkmp.player.PlatformContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


        
        val api = SpotifyApiImpl()
        val root = MusicRootImpl(
            componentContext = defaultComponentContext(),
            api = api,
            mediaPlayerController = MediaPlayerController(PlatformContext(applicationContext))
        )
        setContent {
            MainAndroid(root)
        }
    }
}


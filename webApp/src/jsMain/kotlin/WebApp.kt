
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.nimesh.vasani.melodybeatblastkmp.decompose.MusicRootImpl
import com.nimesh.vasani.melodybeatblastkmp.network.SpotifyApiImpl
import com.nimesh.vasani.melodybeatblastkmp.player.MediaPlayerController
import com.nimesh.vasani.melodybeatblastkmp.player.PlatformContext
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val lifecycle = LifecycleRegistry()
        val rootComponent =
            MusicRootImpl(
                componentContext = DefaultComponentContext(
                    lifecycle = lifecycle,
                ), api = SpotifyApiImpl(), mediaPlayerController = MediaPlayerController(platformContext = PlatformContext())
            )

        lifecycle.resume()
        CanvasBasedWindow("Melody-BeatBlast ") {
            CommonMainWeb(rootComponent)
        }
    }
}




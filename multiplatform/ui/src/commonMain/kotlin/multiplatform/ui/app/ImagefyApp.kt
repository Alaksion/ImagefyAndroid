package multiplatform.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import kotlinx.coroutines.flow.collectLatest
import multiplatform.ui.di.appModule
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

@Composable
fun ImagefyApp(
    plugin: ImagefyAppPlugin
) {
    withDI(appModule) {
        Navigator(
            screens = listOf()
        ) { navigator ->
            AppEffects(plugin = plugin)
            CurrentScreen()
        }
    }
}

@Composable
private fun AppEffects(
    plugin: ImagefyAppPlugin,
) {
    val sdk by rememberInstance<UnsplashWrapperSdk>()
    val navigator = LocalNavigator.current

    LaunchedEffect(plugin.loginListener) {
        plugin.loginListener.loginEvent.collectLatest { authCode -> }
    }
    DisposableEffect(plugin.loginListener) {
        onDispose { plugin.loginListener.onDispose() }
    }
    LaunchedEffect(key1 = plugin) {
        sdk.initialize(
            apiKey = plugin.keys.apiKey,
            privateKey = plugin.keys.private
        )
    }
}

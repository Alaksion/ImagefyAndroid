package multiplatform.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import kotlinx.coroutines.flow.collectLatest
import multiplatform.navigation.SharedScreens
import multiplatform.ui.design.theme.ImagefyTheme
import multiplatform.ui.listeners.AppHttpListener
import org.kodein.di.compose.rememberInstance

@Composable
fun ImagefyApp(
    plugin: ImagefyAppPlugin
) {
    ImagefyTheme {
        Navigator(
            screens = listOf(rememberScreen(SharedScreens.PreLogin))
        ) { _ ->
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
    val httpListener by rememberInstance<AppHttpListener>()
    val navigator = LocalNavigator.current

    LaunchedEffect(plugin.loginListener) {
        plugin.loginListener.loginEvent.collectLatest { authCode ->
            navigator?.push(
                ScreenRegistry.get(SharedScreens.LoginHandler(authCode = authCode))
            )
        }
    }
    DisposableEffect(plugin.loginListener) {
        onDispose { plugin.loginListener.onDispose() }
    }
    LaunchedEffect(key1 = plugin) {
        sdk.initialize(
            apiKey = BuildKonfig.publicKey,
            privateKey = BuildKonfig.privateKey,
            httpListener = httpListener
        )
    }
}

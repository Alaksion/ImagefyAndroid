package io.github.alaksion.imagefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import io.github.alaksion.imagefy.di.appModule
import io.github.alaksion.imagefy.features.prelogin.loginhandler.LoginHandlerScreen
import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreen
import io.github.alaksion.imagefy.intenthandler.IntentEffect
import io.github.alaksion.imagefy.ui.theme.ImagefyTheme
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import org.kodein.di.compose.rememberDI
import org.kodein.di.compose.withDI
import org.kodein.di.instance

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            withDI(appModule) {
                val sdk: UnsplashWrapperSdk by rememberDI() { instance() }
                val activity = LocalContext.current as ComponentActivity

                LaunchedEffect(key1 = Unit) {
                    sdk.initialize(
                        apiKey = BuildConfig.publicKey,
                        privateKey = BuildConfig.privateKey
                    )
                }
                ImagefyTheme {
                    Navigator(
                        screens = listOf(PreLoginScreen()),
                        key = "main-navigator"
                    ) { navigator ->
                        IntentEffect(
                            activity = activity,
                            onIntent = { capturedIntent ->
                                val authCode = capturedIntent.data?.getQueryParameter("code")
                                authCode?.let {
                                    navigator.push(
                                        LoginHandlerScreen(
                                            authCode = it
                                        )
                                    )
                                }
                            }
                        )
                        CurrentScreen()
                    }
                }
            }
        }
    }
}
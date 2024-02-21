package io.github.alaksion.imagefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.Navigator
import io.github.alaksion.imagefy.di.appModule
import io.github.alaksion.imagefy.features.home.tabs.feed.FeedScreen
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

                LaunchedEffect(key1 = Unit) {
                    sdk.initialize(
                        apiKey = BuildConfig.publicKey,
                        privateKey = BuildConfig.privateKey
                    )
                }

                ImagefyTheme {
                    Navigator(
                        screens = listOf(FeedScreen()),
                        key = "main-navigator"
                    )
                }
            }
        }
    }
}
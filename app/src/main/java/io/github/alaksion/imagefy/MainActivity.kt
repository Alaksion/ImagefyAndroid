package io.github.alaksion.imagefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import multiplatform.ui.app.ImagefyApp
import multiplatform.ui.app.ImagefyAppKeys
import multiplatform.ui.app.ImagefyAppPlugin
import multiplatform.ui.listeners.LoginListener

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagefyApp(
                plugin = ImagefyAppPlugin(
                    keys = ImagefyAppKeys(
                        private = BuildConfig.privateKey,
                        apiKey = BuildConfig.publicKey
                    ),
                    loginListener = LoginListener(
                        activity = this,
                        scope = lifecycleScope
                    )
                )
            )
        }
    }
}
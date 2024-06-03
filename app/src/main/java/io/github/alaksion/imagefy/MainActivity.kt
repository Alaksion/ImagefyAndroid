package io.github.alaksion.imagefy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import multiplatform.ui.app.ImagefyApp
import multiplatform.ui.app.ImagefyAppPlugin
import multiplatform.ui.listeners.LoginListener
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class MainActivity : ComponentActivity(), DIAware {

    override val di: DI by closestDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagefyApp(
                plugin = ImagefyAppPlugin(
                    loginListener = LoginListener(
                        activity = this,
                        scope = lifecycleScope
                    )
                )
            )
        }
    }
}
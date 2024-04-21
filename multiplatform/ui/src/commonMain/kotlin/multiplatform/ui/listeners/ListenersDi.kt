package multiplatform.ui.listeners

import org.kodein.di.DI
import org.kodein.di.bindSingleton

internal val listenersModule = DI.Module("imagefy-listeners") {
    bindSingleton<AppHttpListener> {
        AppHttpListener()
    }
}
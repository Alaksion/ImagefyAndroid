package multiplatform.ui.di

import multiplatform.ui.listeners.listenersModule
import multiplatform.ui.screens.screensModule
import mutiplatform.session.di.sessionModule
import org.kodein.di.DI

internal val appModule = DI.Module("imagefy-di") {
    import(unsplashModule)
    import(screensModule)
    import(sessionModule)
    import(listenersModule)
}
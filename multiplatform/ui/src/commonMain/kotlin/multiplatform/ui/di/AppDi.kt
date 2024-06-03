package multiplatform.ui.di

import multiplatform.foundation.logger.di.loggerModule
import multiplatform.ui.listeners.listenersModule
import mutiplatform.session.di.sessionModule
import org.kodein.di.DI

val applicationModule = DI.Module("imagefy-di") {
    import(unsplashModule)
    import(sessionModule)
    import(listenersModule)
    import(loggerModule)
}
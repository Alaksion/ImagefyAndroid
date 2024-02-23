package platform.session.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import platform.session.AuthHandler
import platform.session.AuthHandlerImpl

val sessionModule = DI.Module("platform-session") {

    bindSingleton<AuthHandler> {
        AuthHandlerImpl(
            sessionHandler = instance(),
            auth = instance()
        )
    }

}
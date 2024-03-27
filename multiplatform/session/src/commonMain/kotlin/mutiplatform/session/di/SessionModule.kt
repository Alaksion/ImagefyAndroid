package mutiplatform.session.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import mutiplatform.session.AuthHandler
import mutiplatform.session.AuthHandlerImpl
import mutiplatform.session.SessionHandler
import mutiplatform.session.SessionHandlerImpl

val sessionModule = DI.Module("platform-session") {

    bindSingleton<AuthHandler> {
        AuthHandlerImpl(
            auth = instance(),
            userSingleton = UserSingleton
        )
    }

    bindSingleton<SessionHandler> {
        SessionHandlerImpl(
            userSingleton = UserSingleton,
            userRepository = instance()
        )
    }

}
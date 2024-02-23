package platform.session.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import platform.session.AuthHandler
import platform.session.AuthHandlerImpl
import platform.session.SessionHandler
import platform.session.SessionHandlerImpl
import platform.session.UserSingleton

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
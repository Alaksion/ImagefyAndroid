package mutiplatform.session.di

import mutiplatform.session.SessionHandler
import mutiplatform.session.SessionHandlerImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val sessionModule = DI.Module("platform-session") {

    bindSingleton<SessionHandler> {
        SessionHandlerImpl(
            userRepository = instance(),
            auth = instance()
        )
    }

}
package io.github.alaksion.imagefy.features.prelogin

import io.github.alaksion.imagefy.features.prelogin.loginhandler.LoginHandlerScreenModel
import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreenModel
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val preloginModule = DI.Module("prelogin") {
    bindProvider {
        PreLoginScreenModel(
            sessionHandler = instance(),
            dispatcher = Dispatchers.Default
        )
    }
    bindProvider {
        LoginHandlerScreenModel(
            dispatcher = Dispatchers.Default,
            authHandler = instance()
        )
    }
}
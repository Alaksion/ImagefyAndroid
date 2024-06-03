package multiplatform.features.screens

import cafe.adriel.voyager.core.registry.screenModule
import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreen
import multiplatform.features.screens.loginhandler.LoginHandlerScreen
import multiplatform.navigation.SharedScreens

val featureScreenModule = screenModule {
    register<SharedScreens.PreLogin> {
        PreLoginScreen()
    }

    register<SharedScreens.LoginHandler> {
        LoginHandlerScreen(authCode = it.authCode)
    }
}
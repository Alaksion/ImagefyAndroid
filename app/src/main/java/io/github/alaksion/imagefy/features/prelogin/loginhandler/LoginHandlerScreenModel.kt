package io.github.alaksion.imagefy.features.prelogin.loginhandler

import io.github.alaksion.imagefy.Config
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.stateScreenmodel.UiMode
import multiplatform.uiEvent.UiEventQueue
import multiplatform.uiEvent.UiEventQueueHandler
import platform.session.AuthHandler

internal enum class LoginHandlerEvents {
    Success;
}

internal class LoginHandlerScreenModel(
    dispatcher: CoroutineDispatcher,
    private val authHandler: AuthHandler,
) : StateScreenModel<Unit>(
    dispatcher = dispatcher,
    initialMode = UiMode.Loading,
    initialState = Unit
), UiEventQueue<LoginHandlerEvents> by UiEventQueueHandler() {

    fun handleLogin(
        accessCode: String
    ) = runCatching(
        block = {
            authHandler.signIn(
                accessCode = accessCode,
                redirectUri = Config.AUTH_REDIRECT_URI
            )
            emitEvent(LoginHandlerEvents.Success)
        }
    )
}
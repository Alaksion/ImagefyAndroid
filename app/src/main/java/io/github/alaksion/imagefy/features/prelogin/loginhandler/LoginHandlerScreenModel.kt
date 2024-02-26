package io.github.alaksion.imagefy.features.prelogin.loginhandler

import io.github.alaksion.imagefy.Config
import io.github.alaksion.stateviewmodel.StateScreenModel
import io.github.alaksion.stateviewmodel.UiMode
import kotlinx.coroutines.CoroutineDispatcher
import platform.session.AuthHandler
import platform.uievent.UiEventQueue
import platform.uievent.UiEventQueueHandler

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
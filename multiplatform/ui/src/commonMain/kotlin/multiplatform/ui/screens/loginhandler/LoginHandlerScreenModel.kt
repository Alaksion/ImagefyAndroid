package multiplatform.ui.screens.loginhandler

import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.stateScreenmodel.UiMode
import multiplatform.ui.app.Config
import multiplatform.uiEvent.UiEventQueue
import multiplatform.uiEvent.UiEventQueueHandler
import mutiplatform.session.SessionHandler

internal enum class LoginHandlerEvents {
    Success;
}

internal class LoginHandlerScreenModel(
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
    private val authHandler: SessionHandler,
) : StateScreenModel<Unit>(
    dispatcher = dispatcher,
    initialMode = UiMode.Loading,
    initialState = Unit,
    logger = logger
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
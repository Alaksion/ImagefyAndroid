package io.github.alaksion.imagefy.features.prelogin.prelogin


import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.uiEvent.UiEventQueue
import multiplatform.uiEvent.UiEventQueueHandler
import mutiplatform.session.SessionHandler

internal enum class PreLoginEvents {
    Proceed;
}

internal class PreLoginScreenModel(
    private val sessionHandler: SessionHandler,
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
) : StateScreenModel<Unit>(
    initialState = Unit,
    dispatcher = dispatcher,
    logger = logger
), UiEventQueue<PreLoginEvents> by UiEventQueueHandler() {

    fun initialize() {
        runCatching(
            block = {
                sessionHandler.refreshUser()
                emitEvent(PreLoginEvents.Proceed)
            },
            showLoading = true
        )
    }
}
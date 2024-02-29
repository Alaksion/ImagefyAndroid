package io.github.alaksion.imagefy.features.prelogin.prelogin


import io.github.alaksion.stateviewmodel.StateScreenModel
import kotlinx.coroutines.CoroutineDispatcher
import platform.session.SessionHandler
import platform.uievent.UiEventQueue
import platform.uievent.UiEventQueueHandler

internal enum class PreLoginEvents {
    Proceed;
}

internal class PreLoginScreenModel(
    private val sessionHandler: SessionHandler,
    dispatcher: CoroutineDispatcher
) : StateScreenModel<Unit>(
    initialState = Unit,
    dispatcher = dispatcher
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
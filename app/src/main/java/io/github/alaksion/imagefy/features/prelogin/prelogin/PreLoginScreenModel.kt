package io.github.alaksion.imagefy.features.prelogin.prelogin

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import platform.session.SessionHandler
import platform.uievent.UiEventQueue
import platform.uievent.UiEventQueueHandler

internal enum class PreLoginEvents {
    Proceed;
}

internal class PreLoginScreenModel(
    private val sessionHandler: SessionHandler,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel, UiEventQueue<PreLoginEvents> by UiEventQueueHandler() {

    fun initialize() {
        screenModelScope.launch(dispatcher) {
            sessionHandler.refreshUser()
            emitEvent(PreLoginEvents.Proceed)
        }
    }
}
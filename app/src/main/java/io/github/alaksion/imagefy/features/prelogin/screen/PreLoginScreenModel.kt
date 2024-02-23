package io.github.alaksion.imagefy.features.prelogin.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import platform.session.SessionHandler

internal class PreLoginScreenModel(
    private val sessionHandler: SessionHandler,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    fun initialize() {
        screenModelScope.launch(dispatcher) {
            sessionHandler.refreshUser()
            // Launch UI Event
        }
    }
}
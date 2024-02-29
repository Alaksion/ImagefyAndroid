package io.github.alaksion.imagefy.features.home.tabs.settings

import io.github.alaksion.stateviewmodel.StateScreenModel
import kotlinx.coroutines.CoroutineDispatcher
import platform.session.AuthHandler
import platform.session.SessionHandler
import platform.uievent.UiEventQueue
import platform.uievent.UiEventQueueHandler

internal class SettingsTabScreenModel(
    dispatcher: CoroutineDispatcher,
    private val sessionHandler: SessionHandler,
    private val authHandler: AuthHandler,
) : StateScreenModel<SettingsTabState>(
    initialState = SettingsTabState(),
    dispatcher = dispatcher
), UiEventQueue<SettingsTabEvents> by UiEventQueueHandler() {

    fun initialize() = setState(
        block = {
            this.copy(
                showLogoutButton = sessionHandler.currentUser != null
            )
        }
    )

    fun logout() {
        setState(
            showLoading = false,
            block = {
                authHandler.signOut()
                emitEvent(SettingsTabEvents.Logout)
                this.copy(showLogoutButton = false)
            }
        )
    }

}
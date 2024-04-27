package multiplatform.ui.screens.home.tabs.settings

import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import multiplatform.uiEvent.UiEventQueue
import multiplatform.uiEvent.UiEventQueueHandler
import mutiplatform.session.SessionHandler

internal class SettingsTabScreenModel(
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
    private val sessionHandler: SessionHandler,
) : StateScreenModel<SettingsTabState>(
    initialState = SettingsTabState(),
    dispatcher = dispatcher,
    logger = logger
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
                sessionHandler.signOut()
                emitEvent(SettingsTabEvents.Logout)
                this.copy(showLogoutButton = false)
            }
        )
    }

}
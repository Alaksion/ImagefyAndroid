package multiplatform.features.screens.home.tabs.profile

import io.github.alaksion.unsplashwrapper.api.models.currentuser.domain.CurrentUser
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel
import mutiplatform.session.SessionHandler

internal data class ProfileTabState(
    val profile: CurrentUser? = null
)

internal class ProfileTabScreenModel(
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
    private val sessionHandler: SessionHandler,
) : StateScreenModel<ProfileTabState>(
    initialState = ProfileTabState(),
    dispatcher = dispatcher,
    logger = logger
) {

    fun initialize() {
        if (sessionHandler.currentUser != currentData.profile) {
            setState(
                block = {
                    this.copy(
                        profile = sessionHandler.currentUser
                    )
                }
            )
        }
    }

}
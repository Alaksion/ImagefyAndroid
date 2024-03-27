package io.github.alaksion.imagefy.features.home.tabs.profile

import io.github.alaksion.unsplashwrapper.api.models.currentuser.domain.CurrentUser
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.StateScreenModel
import mutiplatform.session.SessionHandler

internal data class ProfileTabState(
    val profile: CurrentUser? = null
)

internal class ProfileTabScreenModel(
    dispatcher: CoroutineDispatcher,
    private val sessionHandler: mutiplatform.session.SessionHandler,
) : StateScreenModel<ProfileTabState>(
    initialState = ProfileTabState(),
    dispatcher = dispatcher
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
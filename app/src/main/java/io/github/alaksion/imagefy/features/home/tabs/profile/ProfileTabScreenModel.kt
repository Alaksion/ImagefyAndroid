package io.github.alaksion.imagefy.features.home.tabs.profile

import io.github.alaksion.stateviewmodel.StateScreenModel
import io.github.alaksion.unsplashwrapper.api.currentuser.domain.model.CurrentUser
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
import kotlinx.coroutines.CoroutineDispatcher
import platform.session.SessionHandler

internal data class ProfileTabState(
    val profile: CurrentUser? = null
)

internal class ProfileTabScreenModel(
    dispatcher: CoroutineDispatcher,
    private val sessionHandler: SessionHandler,
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
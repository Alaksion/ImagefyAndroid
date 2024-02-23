package platform.session

import io.github.alaksion.unsplashwrapper.api.currentuser.domain.UnsplashCurrentUserRepository
import io.github.alaksion.unsplashwrapper.api.currentuser.domain.model.CurrentUser

interface SessionHandler {
    val currentUser: CurrentUser?
    suspend fun refreshUser()
}

internal class SessionHandlerImpl(
    private val userSingleton: UserSingleton,
    private val userRepository: UnsplashCurrentUserRepository,
) : SessionHandler {

    override val currentUser: CurrentUser? = userSingleton.currentUser

    override suspend fun refreshUser() {
        if (userRepository.isUserLoggedIn()) {
            val user = userRepository.getCurrentUser()
            userSingleton.currentUser = user
        }
    }
}
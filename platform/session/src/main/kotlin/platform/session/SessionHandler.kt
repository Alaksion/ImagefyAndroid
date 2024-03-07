package platform.session

import io.github.alaksion.unsplashwrapper.api.models.currentuser.domain.CurrentUser
import io.github.alaksion.unsplashwrapper.api.repositories.UnsplashCurrentUserRepository

interface SessionHandler {
    val currentUser: CurrentUser?
    suspend fun refreshUser()
}

internal class SessionHandlerImpl(
    private val userSingleton: UserSingleton,
    private val userRepository: UnsplashCurrentUserRepository,
) : SessionHandler {

    override val currentUser: CurrentUser?
        get() {
            return userSingleton.currentUser
        }

    override suspend fun refreshUser() {
        if (userRepository.isUserLoggedIn()) {
            val user = userRepository.getCurrentUser()
            userSingleton.currentUser = user
        }
    }
}
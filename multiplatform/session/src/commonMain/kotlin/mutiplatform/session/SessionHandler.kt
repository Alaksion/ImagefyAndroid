package mutiplatform.session

import io.github.alaksion.unsplashwrapper.api.models.currentuser.domain.CurrentUser
import io.github.alaksion.unsplashwrapper.api.repositories.UnsplashCurrentUserRepository
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth

interface SessionHandler {
    val currentUser: CurrentUser?
    suspend fun refreshUser()

    suspend fun signIn(
        accessCode: String,
        redirectUri: String
    )

    suspend fun signOut()
}

internal class SessionHandlerImpl(
    private val userRepository: UnsplashCurrentUserRepository,
    private val auth: UnsplashAuth
) : SessionHandler {

    override val currentUser: CurrentUser?
        get() = _currentUser

    private var _currentUser: CurrentUser? = null

    override suspend fun refreshUser() {
        if (userRepository.isUserLoggedIn()) {
            val user = userRepository.getCurrentUser()
            _currentUser = user
        }
    }

    override suspend fun signIn(
        accessCode: String,
        redirectUri: String
    ) {
        val user = auth.signIn(
            accessCode = accessCode,
            redirectUri = redirectUri
        )
        _currentUser = user
    }

    override suspend fun signOut() {
        auth.signOut()
        _currentUser = null
    }
}
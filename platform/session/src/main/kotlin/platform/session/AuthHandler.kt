package platform.session

import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth

interface AuthHandler {
    suspend fun signIn(
        accessCode: String,
        redirectUri: String
    )

    suspend fun signOut()
}

internal class AuthHandlerImpl(
    private val auth: UnsplashAuth,
    private val userSingleton: UserSingleton,
) : AuthHandler {

    override suspend fun signIn(
        accessCode: String,
        redirectUri: String
    ) {
        val user = auth.signIn(
            accessCode = accessCode,
            redirectUri = redirectUri
        )
        userSingleton.currentUser = user
    }

    override suspend fun signOut() {
        auth.signOut()
        userSingleton.currentUser = null
    }

}
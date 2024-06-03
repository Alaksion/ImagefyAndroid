package multiplatform.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface SharedScreens : ScreenProvider {
    data object PreLogin : SharedScreens
    data class LoginHandler(val authCode: String) : SharedScreens
}

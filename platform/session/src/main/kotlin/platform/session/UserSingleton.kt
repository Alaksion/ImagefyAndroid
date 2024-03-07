package platform.session

import io.github.alaksion.unsplashwrapper.api.models.currentuser.domain.CurrentUser

internal object UserSingleton {

    var currentUser: CurrentUser? = null

}
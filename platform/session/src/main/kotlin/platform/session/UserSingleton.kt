package platform.session

import io.github.alaksion.unsplashwrapper.api.currentuser.domain.model.CurrentUser

internal object UserSingleton {

    var currentUser: CurrentUser? = null

}
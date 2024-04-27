package multiplatform.ui.screens

import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreenModel
import kotlinx.coroutines.Dispatchers
import multiplatform.ui.screens.debug.httpdetails.HttpDetailsScreenModel
import multiplatform.ui.screens.debug.httplist.HttpListScreenModel
import multiplatform.ui.screens.home.tabs.feed.FeedScreenModel
import multiplatform.ui.screens.home.tabs.profile.ProfileTabScreenModel
import multiplatform.ui.screens.home.tabs.search.SearchTabScreenModel
import multiplatform.ui.screens.home.tabs.settings.SettingsTabScreenModel
import multiplatform.ui.screens.loginhandler.LoginHandlerScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

internal val screensModule = DI.Module("prelogin") {
    bindProvider {
        PreLoginScreenModel(
            sessionHandler = instance(),
            dispatcher = Dispatchers.Default,
            logger = instance()
        )
    }
    bindProvider {
        LoginHandlerScreenModel(
            dispatcher = Dispatchers.Default,
            authHandler = instance(),
            logger = instance()
        )
    }
    bindProvider {
        FeedScreenModel(
            dispatcher = Dispatchers.Default,
            photosRepository = instance(),
            sessionHandler = instance(),
            logger = instance()
        )
    }

    bindProvider {
        ProfileTabScreenModel(
            dispatcher = Dispatchers.Default,
            sessionHandler = instance(),
            logger = instance()
        )
    }

    bindProvider {
        SettingsTabScreenModel(
            dispatcher = Dispatchers.Default,
            sessionHandler = instance(),
            logger = instance()
        )
    }

    bindProvider {
        SearchTabScreenModel(
            dispatcher = Dispatchers.Default,
            searchRepository = instance(),
            logger = instance()
        )
    }

    bindProvider {
        HttpListScreenModel(
            dispatcher = Dispatchers.Default,
            httpListener = instance(),
            logger = instance()
        )
    }

    bindProvider {
        HttpDetailsScreenModel(
            dispatcher = Dispatchers.Default,
            httpListener = instance(),
            logger = instance()
        )
    }
}
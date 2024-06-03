package multiplatform.features.screens

import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreenModel
import kotlinx.coroutines.Dispatchers
import multiplatform.features.screens.debug.httpdetails.HttpDetailsScreenModel
import multiplatform.features.screens.debug.httplist.HttpListScreenModel
import multiplatform.features.screens.home.tabs.feed.FeedScreenModel
import multiplatform.features.screens.home.tabs.profile.ProfileTabScreenModel
import multiplatform.features.screens.home.tabs.search.SearchTabScreenModel
import multiplatform.features.screens.home.tabs.settings.SettingsTabScreenModel
import multiplatform.features.screens.loginhandler.LoginHandlerScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val featuresDiModule = DI.Module("features") {
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
package multiplatform.ui.screens

import io.github.alaksion.imagefy.features.prelogin.prelogin.PreLoginScreenModel
import kotlinx.coroutines.Dispatchers
import multiplatform.ui.screens.debug.list.HttpListDebugViewScreenModel
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
            dispatcher = Dispatchers.Default
        )
    }
    bindProvider {
        LoginHandlerScreenModel(
            dispatcher = Dispatchers.Default,
            authHandler = instance()
        )
    }
    bindProvider {
        FeedScreenModel(
            dispatcher = Dispatchers.Default,
            photosRepository = instance(),
            sessionHandler = instance()
        )
    }

    bindProvider {
        ProfileTabScreenModel(
            dispatcher = Dispatchers.Default,
            sessionHandler = instance()
        )
    }

    bindProvider {
        SettingsTabScreenModel(
            dispatcher = Dispatchers.Default,
            sessionHandler = instance(),
        )
    }

    bindProvider {
        SearchTabScreenModel(
            dispatcher = Dispatchers.Default,
            searchRepository = instance()
        )
    }

    bindProvider {
        HttpListDebugViewScreenModel(
            dispatcher = Dispatchers.Default,
            httpListener = instance()
        )
    }
}
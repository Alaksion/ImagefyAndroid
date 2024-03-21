package io.github.alaksion.imagefy.features.home

import io.github.alaksion.imagefy.features.home.tabs.feed.FeedScreenModel
import io.github.alaksion.imagefy.features.home.tabs.profile.ProfileTabScreenModel
import io.github.alaksion.imagefy.features.home.tabs.search.SearchTabScreenModel
import io.github.alaksion.imagefy.features.home.tabs.settings.SettingsTabScreenModel
import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val homeModule = DI.Module("home-di") {
    bindProvider {
        FeedScreenModel(
            dispatcher = Dispatchers.Default,
            photosRepository = instance()
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
            authHandler = instance()
        )
    }

    bindProvider {
        SearchTabScreenModel(
            dispatcher = Dispatchers.Default,
            searchRepository = instance()
        )
    }
}
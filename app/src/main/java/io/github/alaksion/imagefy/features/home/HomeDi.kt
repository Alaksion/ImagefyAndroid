package io.github.alaksion.imagefy.features.home

import io.github.alaksion.imagefy.features.home.tabs.feed.FeedScreenModel
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
}
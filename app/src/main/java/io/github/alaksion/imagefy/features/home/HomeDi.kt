package io.github.alaksion.imagefy.features.home

import kotlinx.coroutines.Dispatchers
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val homeModule = DI.Module("home-di") {
    bindProvider {
        HomeViewModel(
            dispatcher = Dispatchers.Default,
            photosRepository = instance()
        )
    }
}
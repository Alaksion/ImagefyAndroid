package io.github.alaksion.imagefy.di

import io.github.alaksion.imagefy.features.home.homeModule
import org.kodein.di.DI
import platform.session.di.sessionModule

val appModule = DI {
    import(unsplashModule)
    import(homeModule)
    import(sessionModule)
}
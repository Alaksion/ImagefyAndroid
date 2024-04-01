package multiplatform.ui.di

import org.kodein.di.DI

internal val appModule = DI.Module("imagefy-di") {
    import(unsplashModule)
}
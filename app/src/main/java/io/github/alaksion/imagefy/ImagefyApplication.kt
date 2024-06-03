package io.github.alaksion.imagefy

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import multiplatform.features.screens.featureScreenModule
import multiplatform.features.screens.featuresDiModule
import multiplatform.ui.di.applicationModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class ImagefyApplication : Application(), DIAware {

    override val di: DI = DI.lazy {
        import(featuresDiModule)
        import(applicationModule)
    }

    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            featureScreenModule()
        }
    }

}
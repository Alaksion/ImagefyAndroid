package io.github.alaksion.imagefy

import android.app.Application
import multiplatform.ui.di.applicationModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class ImagefyApplication : Application(), DIAware {

    override val di: DI = DI.lazy {
        import(applicationModule)
    }

}
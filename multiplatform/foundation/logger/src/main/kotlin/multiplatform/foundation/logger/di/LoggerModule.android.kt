package multiplatform.foundation.logger.di

import multiplatform.foundation.logger.AndroidLogger
import multiplatform.foundation.logger.AppLogger
import org.kodein.di.DI
import org.kodein.di.bindSingleton

actual val loggerModule: DI.Module = DI.Module("multiplatform-android-logger") {
    bindSingleton<AppLogger> { AndroidLogger }
}

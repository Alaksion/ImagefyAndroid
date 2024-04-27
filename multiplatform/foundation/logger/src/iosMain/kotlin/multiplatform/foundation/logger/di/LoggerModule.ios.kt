package multiplatform.foundation.logger.di

import multiplatform.foundation.logger.AppLogger
import multiplatform.foundation.logger.IosLogger
import org.kodein.di.DI
import org.kodein.di.bindSingleton

actual val loggerModule: DI.Module = DI.Module("multiplatform-ios-logger") {
    bindSingleton<AppLogger> { IosLogger }
}
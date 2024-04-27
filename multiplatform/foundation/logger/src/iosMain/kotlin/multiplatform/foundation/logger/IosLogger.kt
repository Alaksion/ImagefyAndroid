package multiplatform.foundation.logger

import platform.Foundation.NSLog


// OSLOG not interoperable with kotlin native
object IosLogger : AppLogger {

    override fun logDebug(tag: String, message: String) {
        NSLog("$tag $message")
    }

    override fun logError(tag: String, message: String) {
        NSLog("$tag $message")
    }
}
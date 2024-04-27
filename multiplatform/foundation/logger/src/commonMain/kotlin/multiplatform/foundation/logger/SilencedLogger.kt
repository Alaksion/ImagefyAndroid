package multiplatform.foundation.logger

object SilencedLogger : AppLogger {
    override fun logDebug(tag: String, message: String) = Unit

    override fun logError(tag: String, message: String) = Unit
}
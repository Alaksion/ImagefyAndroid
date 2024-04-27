package multiplatform.foundation.logger

interface AppLogger {
    fun logDebug(tag: String, message: String)
    fun logError(tag: String, message: String)
}
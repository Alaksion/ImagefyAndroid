package multiplatform.foundation.logger

import android.util.Log

internal object AndroidLogger : AppLogger {

    override fun logDebug(tag: String, message: String) {
        Log.d(
            tag,
            message
        )
    }

    override fun logError(tag: String, message: String) {
        Log.e(
            tag,
            message
        )
    }
}
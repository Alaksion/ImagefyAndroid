package multiplatform.ui.logger

import android.util.Log

actual fun log(message: String, tag: String) {
    Log.d(tag, message)
}
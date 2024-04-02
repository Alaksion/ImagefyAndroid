package multiplatform.ui.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class AppContextWrapper(
    val context: Context
)

@Composable
actual fun rememberAppContext(): AppContextWrapper {
    val context = LocalContext.current
    return remember(context) {
        AppContextWrapper(context)
    }
}
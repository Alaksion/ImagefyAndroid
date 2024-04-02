package multiplatform.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

actual class AppContextWrapper

@Composable
actual fun rememberAppContext(): AppContextWrapper = remember { AppContextWrapper() }
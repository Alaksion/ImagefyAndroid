package multiplatform.ui.utils

import androidx.compose.runtime.Composable

expect class AppContextWrapper

@Composable
expect fun rememberAppContext(): AppContextWrapper
package multiplatform.ui.design.statusbar

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
expect fun StatusBarEffect(
    isDarkMode: Boolean,
    scheme: ColorScheme
)
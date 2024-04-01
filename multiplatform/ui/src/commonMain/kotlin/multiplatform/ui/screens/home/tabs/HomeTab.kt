package multiplatform.ui.screens.home.tabs

import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.navigator.tab.Tab

internal interface HomeTab : Tab {
    val unselectedIcon: ImageVector
    val selectedIcon: ImageVector
    val contentDescription: String
}

package io.github.alaksion.imagefy.features.home.tabs.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.imagefy.features.home.tabs.HomeTab

internal object SettingsTab : HomeTab {

    override val unselectedIcon = Icons.Outlined.Settings
    override val selectedIcon = Icons.Filled.Settings
    override val contentDescription = "Settings tab"
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 3u,
                title = "",
                icon = null
            )
        }

    @Composable
    override fun Content() {
        Text("Settings tab")
    }

}
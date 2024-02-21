package io.github.alaksion.imagefy.features.home.tabs.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.imagefy.features.home.tabs.HomeTab

internal object SearchTab : HomeTab {
    override val unselectedIcon = Icons.Outlined.Search
    override val selectedIcon = Icons.Filled.Search
    override val contentDescription = "Search tab"
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 1u,
                title = "",
                icon = null
            )
        }

    @Composable
    override fun Content() {
        Text("Search tab")
    }
}
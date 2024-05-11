package multiplatform.ui.screens.home

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import cafe.adriel.voyager.navigator.tab.Tab
import kotlinx.collections.immutable.persistentListOf
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.feed.FeedTab
import multiplatform.ui.screens.home.tabs.profile.ProfileTab
import multiplatform.ui.screens.home.tabs.search.SearchTab
import multiplatform.ui.screens.home.tabs.settings.SettingsTab

@Composable
internal fun HomeNavBar(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    onTabSelected: (HomeTab) -> Unit
) {
    val items = remember {
        persistentListOf(
            FeedTab,
            SearchTab,
            ProfileTab,
            SettingsTab
        )
    }
    BottomAppBar(
        modifier = modifier,
        actions = {
            items.forEach { tab ->
                IconButton(
                    onClick = { onTabSelected(tab) }
                ) {
                    Icon(
                        imageVector = if (selectedTab == tab) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.contentDescription
                    )
                }
            }
        }
    )
}
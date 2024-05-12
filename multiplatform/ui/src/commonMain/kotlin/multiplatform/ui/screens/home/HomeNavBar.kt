package multiplatform.ui.screens.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import kotlinx.collections.immutable.persistentListOf
import multiplatform.ui.screens.home.tabs.feed.FeedTab
import multiplatform.ui.screens.home.tabs.profile.ProfileTab
import multiplatform.ui.screens.home.tabs.search.SearchTab
import multiplatform.ui.screens.home.tabs.settings.SettingsTab

@Composable
internal fun HomeNavBar(
    modifier: Modifier = Modifier,
) {
    val navigator = LocalTabNavigator.current

    val items = remember {
        persistentListOf(
            FeedTab,
            SearchTab,
            ProfileTab,
            SettingsTab
        )
    }
    NavigationBar(
        modifier = modifier,
    ) {
        items.forEach { tab ->
            NavigationBarItem(
                selected = navigator.current == tab,
                icon = {
                    Icon(
                        imageVector = if (navigator.current == tab) tab.selectedIcon else tab.unselectedIcon,
                        contentDescription = tab.contentDescription
                    )
                },
                onClick = { navigator.current = tab },
                alwaysShowLabel = false
            )
        }
    }
}
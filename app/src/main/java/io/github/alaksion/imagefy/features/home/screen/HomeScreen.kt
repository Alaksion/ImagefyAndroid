package io.github.alaksion.imagefy.features.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import io.github.alaksion.imagefy.features.home.tabs.HomeTab
import io.github.alaksion.imagefy.features.home.tabs.feed.FeedTab
import io.github.alaksion.imagefy.features.home.tabs.profile.ProfileTab
import io.github.alaksion.imagefy.features.home.tabs.search.SearchTab
import io.github.alaksion.imagefy.features.home.tabs.settings.SettingsTab
import kotlinx.collections.immutable.persistentListOf

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun HomeScreenContent() {
        TabNavigator(
            FeedTab,
        ) { navigator ->
            Scaffold(
                content = { scaffoldPadding ->
                    Surface(modifier = Modifier.padding(scaffoldPadding)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomBar(
                        modifier = Modifier.fillMaxWidth(),
                        selectedTab = navigator.current,
                        onTabSelected = { selectedTab ->
                            navigator.current = selectedTab
                        },
                    )
                }
            )
        }
    }

    @Composable
    private fun BottomBar(
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
}
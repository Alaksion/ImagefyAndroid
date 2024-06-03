package multiplatform.features.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import multiplatform.features.screens.home.tabs.feed.FeedTab

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }

    @Composable
    fun HomeScreenContent() {
        TabNavigator(
            FeedTab,
        ) {
            CurrentTab()
        }
    }
}
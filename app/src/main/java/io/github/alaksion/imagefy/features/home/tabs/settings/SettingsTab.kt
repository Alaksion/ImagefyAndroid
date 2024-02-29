package io.github.alaksion.imagefy.features.home.tabs.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.imagefy.features.home.tabs.HomeTab
import io.github.alaksion.imagefy.features.home.tabs.feed.FeedTab
import io.github.alaksion.imagefy.features.home.tabs.settings.components.SettingsAction
import io.github.alaksion.imagefy.features.home.tabs.settings.components.SettingsForm
import platform.uievent.UiEventEffect

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
        val model = rememberScreenModel<SettingsTabScreenModel>()
        val state by model.state.collectAsState()
        val tabNavigator = LocalTabNavigator.current

        LaunchedEffect(key1 = model) {
            model.initialize()
        }

        UiEventEffect(eventSource = model) { event ->
            when (event) {
                SettingsTabEvents.Logout -> tabNavigator.current = FeedTab
            }
        }

        SettingsForm(
            state = state.data,
            onAction = { action ->
                when (action) {
                    SettingsAction.Logout -> model.logout()
                }
            }
        )
    }
}
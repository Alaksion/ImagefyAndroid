package multiplatform.ui.screens.home.tabs.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.screens.debug.httplist.HttpListScreen
import multiplatform.ui.screens.home.HomeNavBar
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.feed.FeedTab
import multiplatform.ui.screens.home.tabs.settings.components.SettingsAction
import multiplatform.ui.screens.home.tabs.settings.components.SettingsForm
import multiplatform.uiEvent.UiEventEffect

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
        val navigator = LocalNavigator.current?.parent

        LaunchedEffect(key1 = model) {
            model.initialize()
        }

        UiEventEffect(eventSource = model) { event ->
            when (event) {
                SettingsTabEvents.Logout -> tabNavigator.current = FeedTab
            }
        }

        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                HomeNavBar(
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            content = { scaffoldPadding ->
                state.View(
                    contentView = {
                        SettingsForm(
                            modifier = Modifier
                                .padding(scaffoldPadding)
                                .fillMaxSize()
                                .padding(ImagefySpacing.Large),
                            state = state.data,
                            onAction = { action ->
                                when (action) {
                                    SettingsAction.Logout -> model.logout()
                                    SettingsAction.DebugView -> navigator?.push(HttpListScreen())
                                }
                            }
                        )
                    },
                    loadingView = {
                        LoadingView(Modifier.fillMaxSize())
                    },
                    errorView = {
                        Text("error")
                    }
                )
            },
        )
    }
}
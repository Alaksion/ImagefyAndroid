package multiplatform.ui.screens.home.tabs.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.LoadingView
import multiplatform.ui.screens.home.HomeNavBar
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.search.components.SearchTabAction
import multiplatform.ui.screens.home.tabs.search.components.SearchTabContent

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
        val model = rememberScreenModel<SearchTabScreenModel>()
        val state by model.state.collectAsState()
        LaunchedEffect(key1 = model) {
            model.search(force = false)
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
                        SearchTabContent(
                            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
                            state = state,
                            onAction = { action ->
                                when (action) {
                                    SearchTabAction.RequestNextPage -> model.loadNextPage()
                                    is SearchTabAction.UpdateQuery -> model.updateQuery(action.query)
                                    SearchTabAction.SubmitSearch -> model.search(force = true)
                                }
                            },
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
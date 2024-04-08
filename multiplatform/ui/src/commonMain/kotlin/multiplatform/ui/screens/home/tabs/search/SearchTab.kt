package multiplatform.ui.screens.home.tabs.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
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

        SearchTabContent(
            state = state,
            onAction = { action ->
                when (action) {
                    SearchTabAction.RequestNextPage -> model.loadNextPage()
                    is SearchTabAction.UpdateQuery -> model.updateQuery(action.query)
                    SearchTabAction.SubmitSearch -> model.search(force = true)
                }
            },
        )
    }
}
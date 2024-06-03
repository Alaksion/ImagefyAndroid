package multiplatform.features.screens.home.tabs.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import multiplatform.features.screens.home.HomeNavBar
import multiplatform.features.screens.home.tabs.HomeTab
import multiplatform.features.screens.home.tabs.search.components.SearchTabAction
import multiplatform.features.screens.home.tabs.search.components.SearchTabContent
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.tokens.ImagefySpacing

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

        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                HomeNavBar(
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = ImagefySpacing.Medium,
                            vertical = ImagefySpacing.Small
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(ImagefySpacing.Medium)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = state.data.query,
                        onValueChange = { model.updateQuery(it) },
                    )
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ManageSearch,
                            contentDescription = null
                        )
                    }
                }
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
                                    SearchTabAction.SubmitSearch -> model.search()
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
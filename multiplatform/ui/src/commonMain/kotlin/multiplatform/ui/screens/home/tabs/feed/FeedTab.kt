package multiplatform.ui.screens.home.tabs.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.feed.components.FeedPhotoCard

internal object FeedTab : HomeTab {

    override val unselectedIcon = Icons.Outlined.Home
    override val selectedIcon = Icons.Filled.Home
    override val contentDescription = "Feed tab"
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "",
                icon = null
            )
        }

    @Composable
    override fun Content() {
        val model: FeedScreenModel = rememberScreenModel()
        val state by model.state.collectAsState()
        LaunchedEffect(key1 = model) { model.initialize() }

        FeedTabContent(
            state = state.data,
        )
    }

}

@Composable
private fun FeedTabContent(
    state: FeedState,
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            itemsIndexed(
                items = state.photos,
                key = { _, item -> item.id }
            ) { index, photo ->
                FeedPhotoCard(
                    data = photo,
                    modifier = Modifier.fillMaxWidth(),
                    showSpacer = index != state.photos.lastIndex
                )
            }
        }
    }

}
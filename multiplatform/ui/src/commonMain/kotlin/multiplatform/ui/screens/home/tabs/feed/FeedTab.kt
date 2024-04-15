package multiplatform.ui.screens.home.tabs.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhotoOrderBy
import kotlinx.coroutines.launch
import multiplatform.ui.design.HorizontalSpacer
import multiplatform.ui.design.tokens.UnsplashSpacing
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.feed.components.FeedPhotoCard
import multiplatform.ui.screens.home.tabs.feed.components.OrderByModal

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
            updateOrderBy = model::updateOrderBy
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedTabContent(
    state: FeedState,
    updateOrderBy: (ListPhotoOrderBy) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (sheetState.isVisible) {
            OrderByModal(
                modifier = Modifier.fillMaxWidth(),
                currentOrderBy = state.orderBy,
                onChange = {
                    scope.launch {
                        updateOrderBy(it)
                        sheetState.hide()
                    }
                },
                onDismiss = {
                    scope.launch { sheetState.hide() }
                },
                state = sheetState
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            item(key = 1) {
                Row(
                    modifier = Modifier
                        .padding(
                            horizontal = UnsplashSpacing.Medium,
                            vertical = UnsplashSpacing.Small
                        )
                        .clickable { scope.launch { sheetState.show() } },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = state.orderBy.icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    HorizontalSpacer(UnsplashSpacing.Small)
                    Text(
                        text = state.orderBy.text,
                        style = MaterialTheme.typography.bodySmall
                    )
                    HorizontalSpacer(UnsplashSpacing.XSmall)
                    Icon(
                        imageVector = Icons.Outlined.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
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
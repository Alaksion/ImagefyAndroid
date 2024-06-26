package multiplatform.features.screens.home.tabs.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
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
import multiplatform.features.screens.home.HomeNavBar
import multiplatform.features.screens.home.tabs.HomeTab
import multiplatform.features.screens.home.tabs.feed.components.FeedPhotoCard
import multiplatform.features.screens.home.tabs.feed.components.OrderByModal
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.HorizontalSpacer
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.autoscroll.AutoScroll
import multiplatform.ui.design.tokens.ImagefySpacing

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
        LaunchedEffect(key1 = model) { model.resumeState(force = false) }

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
                        FeedTabContent(
                            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
                            state = state.data,
                            updateOrderBy = model::updateOrderBy,
                            onRequestNextPage = model::loadNextPage,
                            onFavorite = model::favoritePost
                        )
                    },
                    loadingView = {
                        LoadingView(Modifier.fillMaxSize())
                    },
                    errorView = {
                        ErrorView(
                            modifier = Modifier.fillMaxSize(),
                            description = "Check your internet connection",
                            icon = Icons.Outlined.Close,
                            title = "Something went wrong",
                            primaryAction = { model.resumeState(force = true) },
                            primaryActionText = "Retry"
                        )
                    }
                )
            },
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedTabContent(
    modifier: Modifier = Modifier,
    state: FeedState,
    updateOrderBy: (ListPhotoOrderBy) -> Unit,
    onRequestNextPage: () -> Unit,
    onFavorite: (Boolean, String) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val interactionSource = remember { MutableInteractionSource() }

    val showTopScrollButton = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }

    listState.AutoScroll(
        enabled = state.isNextPageLoading.not(),
        offset = 1
    ) {
        onRequestNextPage()
    }

    Column(
        modifier = modifier
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
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                item(key = 1) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(
                                horizontal = ImagefySpacing.Medium,
                                vertical = ImagefySpacing.Small
                            )
                            .clickable(
                                onClick = { scope.launch { sheetState.show() } },
                                interactionSource = interactionSource,
                                indication = null
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = state.orderBy.icon,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        HorizontalSpacer(ImagefySpacing.Small)
                        Text(
                            text = state.orderBy.text,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        HorizontalSpacer(ImagefySpacing.XSmall)
                        Icon(
                            imageVector = Icons.Outlined.ExpandMore,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                        onFavorite = onFavorite,
                        showUserActions = state.isUserLogged
                    )
                    if (index != state.photos.lastIndex) {
                        VerticalSpacer(ImagefySpacing.XSmall)
                        Divider()
                    }
                }
                if (state.isNextPageLoading) {
                    item(key = 2) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            if (showTopScrollButton.value) {
                IconButton(
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(index = 0)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        contentColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ExpandLess,
                        contentDescription = null
                    )
                }
            }
        }

    }

}
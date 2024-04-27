package multiplatform.ui.screens.debug.httplist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import multiplatform.stateScreenmodel.UiState
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.BaseListItem
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.screens.debug.components.HttpCodeTag
import multiplatform.ui.screens.debug.components.HttpMethodTag
import multiplatform.ui.screens.debug.httpdetails.HttpDetailsScreen

internal class HttpListScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<HttpListScreenModel>()
        val state by model.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            model.initialize()
        }
        state.View(
            contentView = {
                HttpListStateContent(
                    state = state,
                    onBackClick = { navigator?.pop() },
                    onItemClick = { navigator?.push(HttpDetailsScreen(it)) }
                )
            },
            loadingView = { LoadingView(Modifier.fillMaxSize()) },
            errorView = { ErrorView() }
        )
    }

}

@Composable
internal fun HttpListStateContent(
    state: UiState<HtppListState>,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    state.View(
        contentView = {
            HttpListContent(
                data = state.data,
                onBackClick = onBackClick,
                onItemClick = onItemClick
            )
        },
        errorView = { ErrorView(Modifier.fillMaxSize()) },
        loadingView = { LoadingView(Modifier.fillMaxSize()) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HttpListContent(
    data: HtppListState,
    onItemClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = "Http Requests",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(ImagefySpacing.Medium),
            verticalArrangement = Arrangement.spacedBy(ImagefySpacing.Medium),
            state = lazyListState
        ) {
            items(
                items = data.requests,
            ) { requestItem ->
                HttpCallListItem(
                    modifier = Modifier.fillMaxWidth(),
                    method = requestItem.method,
                    code = requestItem.code,
                    url = requestItem.url,
                    timeStamp = requestItem.timeStamp,
                    onItemClick = { onItemClick(requestItem.localId) }
                )
            }
        }
    }
}

@Composable
private fun HttpCallListItem(
    modifier: Modifier = Modifier,
    code: Int,
    method: String,
    url: String,
    timeStamp: String,
    onItemClick: () -> Unit,
) {
    Card {
        BaseListItem(
            modifier = modifier.padding(ImagefySpacing.Small),
            leading = {
                Column {
                    HttpMethodTag(method)
                    VerticalSpacer(ImagefySpacing.XSmall)
                    HttpCodeTag(code)
                }
            },
            trailing = {
                IconButton(
                    onClick = onItemClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        null
                    )
                }
            },
            content = {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = url,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                    VerticalSpacer(ImagefySpacing.XSmall)
                    Text(
                        text = timeStamp,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        )
    }

}
package multiplatform.ui.screens.debug.httpdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import multiplatform.stateScreenmodel.UiState
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.scrollcontainer.HorizontalScrollContainer
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.screens.debug.components.HttpCodeTag
import multiplatform.ui.screens.debug.components.HttpMethodTag

internal class HttpDetailsScreen(
    private val httpLocalId: String
) : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel<HttpDetailsScreenModel>()
        val state by model.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(model) {
            model.initialize(httpLocalId)
        }
        HttpDetailsScreenStateContent(
            state = state,
            onBackClick = { navigator?.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HttpDetailsScreenStateContent(
    state: UiState<HttpDetailsState>,
    onBackClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Request Details") },
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
        state.View(
            contentView = {
                HttpDetailsContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    data = state.data
                )
            },
            loadingView = { LoadingView(Modifier.fillMaxSize().padding(it)) },
            errorView = { ErrorView(Modifier.fillMaxSize().padding(it)) }
        )
    }
}

@Composable
private fun HttpDetailsContent(
    modifier: Modifier = Modifier,
    data: HttpDetailsState
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(ImagefySpacing.Medium),
        contentPadding = PaddingValues(ImagefySpacing.Medium)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ImagefySpacing.Small)
            ) {
                HttpMethodTag(method = data.request.method)
                HttpCodeTag(code = data.request.code)
            }
            VerticalSpacer(ImagefySpacing.Medium)
            Text(
                text = "Timestamp",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = data.request.timeStamp,
                style = MaterialTheme.typography.bodySmall
            )
            VerticalSpacer(ImagefySpacing.Medium)
            Text(
                text = "Request URL",
                style = MaterialTheme.typography.bodyLarge
            )
            VerticalSpacer(ImagefySpacing.XSmall)
            HorizontalScrollContainer(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = data.request.url,
                )
            }
        }
        item {
            HorizontalScrollContainer(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = data.request.body,
                )
            }
        }
    }
}
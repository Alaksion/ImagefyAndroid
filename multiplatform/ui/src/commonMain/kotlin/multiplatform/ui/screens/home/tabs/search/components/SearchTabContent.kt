package multiplatform.ui.screens.home.tabs.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import multiplatform.stateScreenmodel.UiMode
import multiplatform.stateScreenmodel.UiState
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.Spacer
import multiplatform.ui.design.autoscroll.AutoScroll
import multiplatform.ui.design.tokens.UnsplashSpacing
import multiplatform.ui.screens.home.tabs.search.SearchTabState

internal sealed interface SearchTabAction {
    data class UpdateQuery(val query: String) : SearchTabAction
    object RequestNextPage : SearchTabAction
    object SubmitSearch : SearchTabAction
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun SearchTabContent(
    state: UiState<SearchTabState>,
    onAction: (SearchTabAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.fillMaxSize()) {
        if (sheetState.isVisible) {
            SearchTabFilterModal(
                state = sheetState,
                onDismiss = {
                    scope.launch { sheetState.hide() }
                }
            )
        }
        Header(
            modifier = Modifier.fillMaxWidth(),
            query = state.data.query,
            onChangeQuery = { onAction(SearchTabAction.UpdateQuery(it)) },
            onSubmit = {
                keyboard?.hide()
                onAction(SearchTabAction.SubmitSearch)
            },
            onOpenFilters = { scope.launch { sheetState.expand() } }
        )
        state.View(
            contentView = {
                ListContent(
                    modifier = Modifier.weight(1f),
                    photos = state.data.photos,
                    onAction = onAction
                )
            },
            errorView = {
                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(1f)
                    ErrorView(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Something went wrong",
                        description = "Check your connection and try again later",
                        icon = Icons.Outlined.Close
                    )
                    Spacer(1f)
                }
            },
            loadingView = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        )

    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    query: String,
    onChangeQuery: (String) -> Unit,
    onSubmit: () -> Unit,
    onOpenFilters: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UnsplashSpacing.Medium)
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = query,
            onValueChange = onChangeQuery,
            placeholder = {
                Text(
                    text = "Search"
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSubmit() }
            )
        )
        IconButton(
            onClick = onOpenFilters,
            content = {
                Icon(
                    imageVector = Icons.Outlined.Tune,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    photos: ImmutableList<SearchPhotosItem>,
    onAction: (SearchTabAction) -> Unit
) {
    val gridState = rememberLazyGridState()

    gridState.AutoScroll {
        onAction(SearchTabAction.RequestNextPage)
    }

    if (photos.isEmpty()) {
        Column(
            modifier = modifier
        ) {
            Spacer(1f)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "No photos found for current filter",
                textAlign = TextAlign.Center
            )
            Spacer(1f)
        }
    } else {
        LazyVerticalGrid(
            state = gridState,
            modifier = modifier,
            columns = GridCells.Fixed(3),
        ) {
            items(
                items = photos,
                key = { it.id }
            ) {
                Text(it.user.name)
            }
        }
    }
}
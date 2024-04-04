package multiplatform.ui.screens.home.tabs.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import multiplatform.ui.design.Spacer
import multiplatform.ui.design.autoscroll.AutoScroll
import multiplatform.ui.screens.home.tabs.search.SearchTabState

internal sealed interface SearchTabAction {
    data class UpdateQuery(val query: String) : SearchTabAction
    object RequestNextPage : SearchTabAction
}

@Composable
internal fun SearchTabContent(
    modifier: Modifier = Modifier,
    state: SearchTabState,
    onAction: (SearchTabAction) -> Unit
) {
    val gridState = rememberLazyGridState()

    gridState.AutoScroll {
        onAction(SearchTabAction.RequestNextPage)
    }

    if (state.photos.isEmpty()) {
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
                items = state.photos,
                key = { it.id }
            ) {
                Text(it.user.name)
            }
        }
    }
}
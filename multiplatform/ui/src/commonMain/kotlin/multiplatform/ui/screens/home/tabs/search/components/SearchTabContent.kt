package multiplatform.ui.screens.home.tabs.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    LazyVerticalGrid(
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
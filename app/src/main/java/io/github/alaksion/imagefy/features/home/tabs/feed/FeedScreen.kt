package io.github.alaksion.imagefy.features.home.tabs.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import io.github.alaksion.imagefy.features.home.tabs.feed.components.HomePhotoCard

internal class FeedScreen : Screen {

    @Composable
    override fun Content() {
        val model: FeedScreenModel = rememberScreenModel()
        val state by model.state.collectAsState()
        LaunchedEffect(key1 = model) { model.initialize() }

        FeedScreenContent(
            state = state.data,
        )
    }

}

@Composable
private fun FeedScreenContent(
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
                HomePhotoCard(
                    data = photo,
                    modifier = Modifier.fillMaxWidth(),
                    showSpacer = index != state.photos.lastIndex
                )
            }
        }
    }

}
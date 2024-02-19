package io.github.alaksion.imagefy.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import io.github.alaksion.imagefy.features.home.components.HomePhotoCard

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val model: HomeScreenModel = rememberScreenModel()
        val state by model.state.collectAsState()
        LaunchedEffect(key1 = model) { model.initialize() }

        HomeScreenContent(
            state = state.data,
            test = model::initialize
        )
    }

}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    test: () -> Unit
) {
    Column {
        Button(onClick = test) {
            Text("Load data")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = state.photos,
                key = { it.id }
            ) { photo ->
                HomePhotoCard(
                    data = photo,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}
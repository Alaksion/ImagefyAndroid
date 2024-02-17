package io.github.alaksion.imagefy.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val model: HomeScreenModel = rememberScreenModel()
        val state by model.state.collectAsState()
        LaunchedEffect(key1 = model) { model.initialize() }

        HomeScreenContent(
            state = state.data
        )
    }

}

@Composable
private fun HomeScreenContent(
    state: HomeState
) {
    Text("hello world")
}
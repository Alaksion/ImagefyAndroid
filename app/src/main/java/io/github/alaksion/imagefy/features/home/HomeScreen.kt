package io.github.alaksion.imagefy.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

internal class HomeScreen : Screen {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }

}

@Composable
private fun HomeScreenContent() {
    Text("hello world")
}
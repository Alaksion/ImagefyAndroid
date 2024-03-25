package io.github.alaksion.imagefy.features.prelogin.prelogin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.alaksion.imagefy.features.home.screen.HomeScreen
import multiplatform.uiEvent.UiEventEffect
import multiplatform.uiEvent.UiEventQueue

internal class PreLoginScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<PreLoginScreenModel>()

        LaunchedEffect(key1 = model) { model.initialize() }
        Events(source = model)

        Scaffold {
            Box(
                modifier = Modifier.padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    private fun Events(
        source: UiEventQueue<PreLoginEvents>
    ) {
        val navigator = LocalNavigator.current

        UiEventEffect(
            eventSource = source,
            onEvent = { event ->
                when (event) {
                    PreLoginEvents.Proceed -> navigator?.replaceAll(HomeScreen())
                }
            }
        )
    }
}
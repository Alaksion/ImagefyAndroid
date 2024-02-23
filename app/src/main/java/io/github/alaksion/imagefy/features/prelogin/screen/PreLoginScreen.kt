package io.github.alaksion.imagefy.features.prelogin.screen

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

internal class PreLoginScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<PreLoginScreenModel>()
        LaunchedEffect(key1 = model) { model.initialize() }

        Scaffold {
            Box(
                modifier = Modifier.padding(it),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
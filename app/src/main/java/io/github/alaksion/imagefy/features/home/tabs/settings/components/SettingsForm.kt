package io.github.alaksion.imagefy.features.home.tabs.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.alaksion.imagefy.design.components.Spacer
import io.github.alaksion.imagefy.design.tokens.UnsplashSpacing
import io.github.alaksion.imagefy.features.home.tabs.settings.SettingsTabState

@Composable
internal fun SettingsForm(
    state: SettingsTabState,
    onAction: (SettingsAction) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(UnsplashSpacing.Large)
        ) {
            Spacer(1f)
            if (state.showLogoutButton) {
                TextButton(
                    onClick = { onAction(SettingsAction.Logout) }
                ) {
                    Text("Log out current account")
                }
            }
        }
    }
}
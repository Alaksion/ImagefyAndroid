package multiplatform.ui.screens.home.tabs.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BugReport
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import multiplatform.ui.design.BaseCard
import multiplatform.ui.design.Spacer
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.screens.home.tabs.settings.SettingsTabState

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
                .padding(ImagefySpacing.Large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(1f)
            if (state.showLogoutButton) {
                TextButton(
                    onClick = { onAction(SettingsAction.Logout) }
                ) {
                    Text("Log out current account")
                }
            }
            BaseCard(
                modifier = Modifier.fillMaxWidth().clickable {
                    onAction(SettingsAction.DebugView)
                },
                leading = {
                    Icon(imageVector = Icons.Outlined.BugReport, contentDescription = null)
                },
                trailing = {
                    Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null)
                },
                content = {
                    Text(text = "Debug View")
                }
            )
        }
    }
}
package multiplatform.ui.screens.debug.httpdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

internal class HttpDetailsScreen(
    private val httpLocalId: String
) : Screen {
    @Composable
    override fun Content() {
        Text(
            text = "http details"
        )
    }
}
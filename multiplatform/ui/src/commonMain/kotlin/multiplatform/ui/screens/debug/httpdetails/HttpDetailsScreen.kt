package multiplatform.ui.screens.debug.httpdetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import multiplatform.stateScreenmodel.UiState
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.LoadingView

internal class HttpDetailsScreen(
    private val httpLocalId: String
) : Screen {
    @Composable
    override fun Content() {
        val model = rememberScreenModel<HttpDetailsScreenModel>()
        val state by model.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(model) {
            model.initialize(httpLocalId)
        }
        HttpDetailsScreenStateContent(
            state = state,
            onBackClick = { navigator?.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HttpDetailsScreenStateContent(
    state: UiState<HttpDetailsState>,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Request Details") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        state.View(
            contentView = {
                HttpDetailsContent(data = state.data)
            },
            loadingView = { LoadingView(Modifier.fillMaxSize()) },
            errorView = { ErrorView(Modifier.fillMaxSize()) }
        )
    }
}

@Composable
private fun HttpDetailsContent(
    data: HttpDetailsState
) {

}
package multiplatform.ui.screens.debug.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.LoadingView
import multiplatform.ui.logger.log

class HttpListDebugViewScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<HttpListDebugViewScreenModel>()
        val state by model.state.collectAsState()

        SideEffect {
            log(message = state.data.requests.size.toString(), tag = "teste")
        }

        LaunchedEffect(Unit) { model.initialize() }
        state.View(
            contentView = {
                DebugViewContent(state.data)
            },
            loadingView = { LoadingView(Modifier.fillMaxSize()) },
            errorView = { ErrorView() }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DebugViewContent(
    state: DebugViewState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Debug View") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = state.requests,
                key = { it.localId }
            ) {
                Text("Hello world")
            }
            item {
                Text("teste")
            }
            item {
                Text("teste")
            }
            item {
                Text("teste")
            }
            item {
                Text("teste")
            }
        }
    }
}
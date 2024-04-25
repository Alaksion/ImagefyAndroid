package multiplatform.ui.screens.debug.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.design.LoadingView

class HttpListDebugViewScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<HttpListDebugViewScreenModel>()
        val state by model.state.collectAsState()

        LaunchedEffect(Unit) {
            model.initialize()
        }
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
                title = {
                    Text(
                        text = "Http Requests",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(
                items = state.requests,
            ) {
                Text(
                    "Hello world${it.code}",
                    modifier = Modifier.fillMaxWidth().background(Color.Yellow)
                )
            }
        }
    }
}
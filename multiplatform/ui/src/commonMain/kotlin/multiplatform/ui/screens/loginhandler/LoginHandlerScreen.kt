package multiplatform.ui.screens.loginhandler

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import multiplatform.stateScreenmodel.View
import multiplatform.ui.design.ErrorView
import multiplatform.ui.screens.home.HomeScreen
import multiplatform.uiEvent.UiEventEffect

internal class LoginHandlerScreen(
    private val authCode: String
) : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel<LoginHandlerScreenModel>()
        val state by model.state.collectAsState()
        val navigator = LocalNavigator.current

        UiEventEffect(eventSource = model) { event ->
            when (event) {
                LoginHandlerEvents.Success -> navigator?.replaceAll(HomeScreen())
            }
        }

        LaunchedEffect(Unit) {
            model.handleLogin(accessCode = authCode)
        }

        Scaffold {
            state.View(
                contentView = { },
                errorView = {
                    ErrorView(
                        title = "Login error title",
                        description = "Login error description",
                        icon = Icons.Outlined.ErrorOutline
                    )
                },
                loadingView = {
                    LoadingView(Modifier.padding(it))
                }
            )
        }
    }

    @Composable
    private fun LoadingView(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }

}
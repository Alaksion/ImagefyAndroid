package multiplatform.features.screens.home.tabs.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.unsplashwrapper.api.models.authorization.domain.AuthorizationScope
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
import kotlinx.collections.immutable.persistentSetOf
import multiplatform.features.screens.home.HomeNavBar
import multiplatform.features.screens.home.tabs.HomeTab
import multiplatform.features.screens.home.tabs.profile.components.ProfileForm
import multiplatform.stateScreenmodel.View
import multiplatform.ui.app.Config
import multiplatform.ui.design.LoadingView
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.utils.rememberAppContext
import multiplatform.ui.utils.requestBrowser
import org.kodein.di.compose.localDI
import org.kodein.di.instance


internal object ProfileTab : HomeTab {
    override val unselectedIcon = Icons.Outlined.Person
    override val selectedIcon = Icons.Filled.Person
    override val contentDescription = "Profile tab"
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 2u,
                title = "",
                icon = null
            )
        }

    @Composable
    override fun Content() {
        val model = rememberScreenModel<ProfileTabScreenModel>()
        val state by model.state.collectAsState()
        val auth by localDI().instance<UnsplashAuth>()
        val context = rememberAppContext()

        LaunchedEffect(key1 = model) { model.initialize() }

        Scaffold(
            modifier = Modifier.navigationBarsPadding(),
            bottomBar = {
                HomeNavBar(
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            content = { scaffoldPadding ->
                state.View(
                    contentView = {
                        ProfileTabContent(
                            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
                            state = state.data,
                            onRequestLogin = {
                                val url = auth.buildAuthorizeUrl(
                                    redirectUri = Config.AUTH_REDIRECT_URI,
                                    scopes = persistentSetOf(
                                        AuthorizationScope.Public,
                                        AuthorizationScope.WriteLikes
                                    )
                                )
                                requestBrowser(url = url, context)
                            }
                        )
                    },
                    loadingView = {
                        LoadingView(Modifier.fillMaxSize())
                    },
                    errorView = {
                        Text("error")
                    }
                )
            },
        )
    }

    @Composable
    fun ProfileTabContent(
        modifier: Modifier = Modifier,
        state: ProfileTabState,
        onRequestLogin: () -> Unit
    ) {
        Column(modifier) {

            if (state.profile == null) {
                Button(onClick = onRequestLogin) {
                    Text("Login")
                }
            } else {
                ProfileForm(
                    data = state.profile,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(ImagefySpacing.Large)
                )
            }
        }
    }
}
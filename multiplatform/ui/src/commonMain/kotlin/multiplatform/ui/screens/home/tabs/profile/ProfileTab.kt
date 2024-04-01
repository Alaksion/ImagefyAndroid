package multiplatform.ui.screens.home.tabs.profile

import androidx.compose.foundation.layout.fillMaxSize
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
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
import multiplatform.ui.design.tokens.UnsplashSpacing
import multiplatform.ui.screens.home.tabs.HomeTab
import multiplatform.ui.screens.home.tabs.profile.components.ProfileForm
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

        LaunchedEffect(key1 = model) { model.initialize() }

        ProfileTabContent(
            state = state.data,
            onRequestLogin = {
//                val url = auth.buildAuthorizeUrl(
//                    redirectUri = Config.AUTH_REDIRECT_URI,
//                    scopes = persistentSetOf(AuthorizationScope.Public)
//                )
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.setData(Uri.parse(url))
//                context.startActivity(intent)
            }
        )
    }

    @Composable
    fun ProfileTabContent(
        state: ProfileTabState,
        onRequestLogin: () -> Unit
    ) {
        Scaffold {
            if (state.profile == null) {
                Button(onClick = onRequestLogin) {
                    Text("Login")
                }
            } else {
                ProfileForm(
                    data = state.profile,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(UnsplashSpacing.Large)
                )
            }
        }
    }
}
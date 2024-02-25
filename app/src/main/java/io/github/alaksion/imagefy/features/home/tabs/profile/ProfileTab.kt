package io.github.alaksion.imagefy.features.home.tabs.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.imagefy.Config
import io.github.alaksion.imagefy.features.home.tabs.HomeTab
import io.github.alaksion.unsplashwrapper.api.authorization.domain.model.AuthorizationScope
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
import kotlinx.collections.immutable.persistentSetOf
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
        val context = LocalContext.current

        ProfileTabContent(
            state = state.data,
            onRequestLogin = {
                val url = auth.buildAuthorizeUrl(
                    redirectUri = Config.AUTH_REDIRECT_URI,
                    scopes = persistentSetOf(AuthorizationScope.Public)
                )
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                context.startActivity(intent)
            }
        )
    }

    @Composable
    fun ProfileTabContent(
        state: ProfileTabState,
        onRequestLogin: () -> Unit
    ) {
        if (state.profile == null) {
            Button(onClick = onRequestLogin) {
                Text("Login")
            }
        } else {
            Text(state.profile.firstName)
        }
    }
}
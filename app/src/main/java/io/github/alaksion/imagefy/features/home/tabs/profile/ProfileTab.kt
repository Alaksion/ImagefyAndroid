package io.github.alaksion.imagefy.features.home.tabs.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.alaksion.imagefy.features.home.tabs.HomeTab
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
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
        val auth = localDI().instance<UnsplashAuth>()
    }

    @Composable
    fun ProfileTabContent(
        onRequestLogin: () -> Unit
    ) {

    }
}
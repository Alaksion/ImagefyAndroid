package io.github.alaksion.imagefy.features.home.tabs.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.alaksion.unsplashwrapper.api.currentuser.domain.model.CurrentUser

@Composable
internal fun ProfileForm(
    data: CurrentUser,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ProfileHeader(
            modifier = Modifier.fillMaxWidth(),
            name = data.firstName + " " + data.lastName,
            username = data.username
        )
    }
}
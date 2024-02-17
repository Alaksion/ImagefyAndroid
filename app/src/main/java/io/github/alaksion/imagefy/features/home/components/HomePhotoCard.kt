package io.github.alaksion.imagefy.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.alaksion.unsplashwrapper.api.photos.domain.domain.models.listphotos.ListPhoto

@Composable
internal fun HomePhotoCard(
    data: ListPhoto,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
    }
}

@Composable
private fun Header(
    name: String,
    username: String,
    profileImageUrl: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {

    }
}

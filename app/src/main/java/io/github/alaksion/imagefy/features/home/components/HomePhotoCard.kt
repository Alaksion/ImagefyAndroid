package io.github.alaksion.imagefy.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.alaksion.imagefy.design.modifiers.shimmer
import io.github.alaksion.unsplashwrapper.api.photos.domain.domain.models.listphotos.ListPhoto
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun HomePhotoCard(
    data: ListPhoto,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Header(
            name = data.user.name,
            username = data.user.username,
            profileImageUrl = data.user.profileImage.small
        )
    }
}

@Composable
private fun Header(
    name: String,
    username: String,
    profileImageUrl: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        KamelImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null,
            resource = asyncPainterResource(data = profileImageUrl),
            onLoading = {
                Box(modifier = Modifier.shimmer())
            },
            onFailure = {
                Box(modifier = Modifier.background(Color.Red))
            }
        )
        Column(Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = username,
            )
        }
    }
}

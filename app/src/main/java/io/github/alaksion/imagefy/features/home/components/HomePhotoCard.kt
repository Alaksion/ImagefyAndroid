package io.github.alaksion.imagefy.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.alaksion.imagefy.design.modifiers.shimmer
import io.github.alaksion.imagefy.design.tokens.UnsplashSpacing
import io.github.alaksion.unsplashwrapper.api.photos.domain.domain.models.listphotos.ListPhoto
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun HomePhotoCard(
    data: ListPhoto,
    modifier: Modifier = Modifier,
    showSpacer: Boolean,
) {
    Column(modifier = modifier) {
        Header(
            name = data.user.name,
            username = data.user.username,
            profileImageUrl = data.user.profileImage.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = UnsplashSpacing.Small,
                    vertical = UnsplashSpacing.XSmall
                )
        )
        Photo(
            url = data.urls.regular,
            modifier = Modifier.fillMaxWidth()
        )
        Footer(
            description = data.description,
            modifier = Modifier.fillMaxWidth()
        )
        if (showSpacer) {
            HorizontalDivider(modifier = Modifier.padding(vertical = UnsplashSpacing.XSmall2))
        }
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
                .size(32.dp)
                .clip(CircleShape)
                .shadow(1.dp),
            contentDescription = null,
            resource = asyncPainterResource(data = profileImageUrl),
            onLoading = {
                Box(
                    modifier = Modifier.shimmer()
                )
            },
            onFailure = {
                Box(modifier = Modifier.background(Color.Red))
            },
            contentScale = ContentScale.FillBounds
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

@Composable
private fun Photo(
    modifier: Modifier = Modifier,
    url: String
) {
    KamelImage(
        modifier = modifier,
        contentDescription = null,
        resource = asyncPainterResource(data = url),
        onLoading = {
            Box(
                modifier = Modifier
                    .shimmer()
                    .fillMaxSize()
            )
        },
        onFailure = {
            Box(modifier = Modifier.background(Color.Red))
        },
    )
}

@Composable
private fun Footer(
    modifier: Modifier = Modifier,
    description: String?,
) {
    val actionPadding = remember(description) {
        if (description != null) {
            PaddingValues(vertical = UnsplashSpacing.XSmall2)
        } else PaddingValues(top = UnsplashSpacing.XSmall2)
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(actionPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Outlined.BookmarkAdd, contentDescription = null)
            }
        }
        description?.let {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = UnsplashSpacing.Small)
            )
        }
    }
}

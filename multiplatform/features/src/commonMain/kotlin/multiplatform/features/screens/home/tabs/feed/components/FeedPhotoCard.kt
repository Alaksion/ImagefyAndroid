package multiplatform.features.screens.home.tabs.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhoto
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.utils.rememberDeviceDimensions
import multiplatform.ui.utils.rememberImageProportionalHeight

@Composable
internal fun FeedPhotoCard(
    data: ListPhoto,
    showUserActions: Boolean,
    modifier: Modifier = Modifier,
    onFavorite: (Boolean, String) -> Unit,
) {
    val dimensions = rememberDeviceDimensions()
    val cardHeight = rememberImageProportionalHeight(
        containerWidth = dimensions.width,
        originalImageHeight = data.height.dp,
        originalImageWidth = data.width.dp
    )
    var localIsLiked by rememberSaveable(data) { mutableStateOf(data.likedByUser) }

    Column(modifier = modifier) {
        Header(
            name = data.user.name,
            username = data.user.username,
            profileImageUrl = data.user.profileImage.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = ImagefySpacing.Small,
                    vertical = ImagefySpacing.XSmall
                ),
            loadingColor = data.color.composeColor
        )
        Photo(
            url = data.urls.regular,
            modifier = Modifier.fillMaxWidth().height(cardHeight),
            loadingBackground = data.color.composeColor
        )
        Footer(
            description = data.description,
            modifier = Modifier.fillMaxWidth(),
            isLiked = localIsLiked,
            onLike = {
                localIsLiked = localIsLiked.not()
                onFavorite(localIsLiked, data.id)
            },
            showActions = showUserActions
        )
    }
}

@Composable
private fun Header(
    name: String,
    username: String,
    profileImageUrl: String,
    loadingColor: Color,
    modifier: Modifier = Modifier
) {
    val painter = asyncPainterResource(data = profileImageUrl)

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
            resource = painter,
            onLoading = {
                Box(modifier = Modifier.background(loadingColor).fillMaxSize())
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
    loadingBackground: Color,
    url: String,
) {
    val loader = asyncPainterResource(data = url)

    KamelImage(
        modifier = modifier,
        contentDescription = null,
        resource = loader,
        onLoading = {
            Box(Modifier.fillMaxSize().background(loadingBackground))
        },
        onFailure = { Box(modifier = Modifier.background(Color.Red)) },
    )
}

@Composable
private fun Footer(
    modifier: Modifier = Modifier,
    description: String?,
    isLiked: Boolean,
    showActions: Boolean,
    onLike: () -> Unit,
) {
    Column(modifier = modifier) {
        if (showActions) {
            FooterActions(
                isLiked = isLiked,
                onLike = onLike,
                modifier = Modifier.fillMaxWidth()
            )
        }
        VerticalSpacer(ImagefySpacing.XSmall)
        description?.let {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ImagefySpacing.Medium)
            )
        }
    }
}

@Composable
private fun FooterActions(
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    val likeIcon = remember(isLiked) {
        if (isLiked) Icons.Filled.Favorite
        else Icons.Outlined.FavoriteBorder
    }

    val likeIconTint = remember(isLiked, colors) {
        if (isLiked) Color.Red
        else colors.onSurface
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = onLike
        ) {
            Icon(imageVector = likeIcon, contentDescription = null, tint = likeIconTint)
        }
        IconButton(onClick = { }) {
            Icon(imageVector = Icons.Outlined.BookmarkAdd, contentDescription = null)
        }
    }
}
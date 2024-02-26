package io.github.alaksion.imagefy.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.alaksion.imagefy.design.tokens.UnsplashSpacing

@Composable
internal fun ErrorView(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    description: String,
    primaryActionText: String? = null,
    primaryAction: (() -> Unit)? = null,
    secondaryActionText: String? = null,
    secondaryAction: (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberVectorPainter(image = icon),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .clip(CircleShape)
                .padding(UnsplashSpacing.XSmall2)
        )
        VerticalSpacer(size = UnsplashSpacing.Large)
        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = description,
        )
        Spacer(weight = 1f)
        if (primaryAction != null && primaryActionText != null) {
            Button(
                onClick = primaryAction
            ) {
                Text(primaryActionText)
            }
            VerticalSpacer(size = UnsplashSpacing.Small)
        }
        if (secondaryAction != null && secondaryActionText != null) {
            Button(
                onClick = secondaryAction
            ) {
                Text(secondaryActionText)
            }
        }
    }
}
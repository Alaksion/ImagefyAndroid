package multiplatform.ui.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
internal fun ErrorView(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.Close,
    title: String = "Something went wrong",
    description: String = "Check your internet connection",
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
                .size(128.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurface)
                .padding(ImagefySpacing.Large),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface)
        )
        VerticalSpacer(size = ImagefySpacing.Large)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(size = ImagefySpacing.XSmall)
        Text(
            text = description,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        VerticalSpacer(size = ImagefySpacing.Large)

        if (primaryAction != null && primaryActionText != null) {
            Button(
                onClick = primaryAction
            ) {
                Text(primaryActionText)
            }
            VerticalSpacer(size = ImagefySpacing.Small)
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
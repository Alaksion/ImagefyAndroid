package multiplatform.ui.screens.home.tabs.feed.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhotoOrderBy
import multiplatform.ui.design.BaseListItem
import multiplatform.ui.design.VerticalSpacer
import multiplatform.ui.design.tokens.ImagefySpacing
import multiplatform.ui.screens.home.tabs.feed.icon
import multiplatform.ui.screens.home.tabs.feed.text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderByModal(
    modifier: Modifier = Modifier,
    currentOrderBy: ListPhotoOrderBy,
    state: SheetState,
    onDismiss: () -> Unit,
    onChange: (ListPhotoOrderBy) -> Unit,
) {
    var localSelection by remember(currentOrderBy) {
        mutableStateOf(currentOrderBy)
    }
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    ModalBottomSheet(
        modifier = modifier,
        sheetState = state,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ImagefySpacing.Medium)
                    .padding(bottom = bottomPadding)
            ) {
                Text(
                    text = "Order photos by"
                )
                VerticalSpacer(ImagefySpacing.Small)
                Divider(modifier = Modifier.fillMaxWidth())
                VerticalSpacer(ImagefySpacing.Small)
                ListPhotoOrderBy.entries.forEach { orderByItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = ImagefySpacing.Small),
                        item = orderByItem,
                        isSelected = orderByItem == localSelection,
                        onClick = { localSelection = orderByItem }
                    )
                }
                VerticalSpacer(ImagefySpacing.Medium)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onChange(localSelection)
                    },
                ) {
                    Text("Select")
                }
            }
        },
        onDismissRequest = onDismiss,
    )
}

@Composable
private fun Card(
    modifier: Modifier = Modifier,
    item: ListPhotoOrderBy,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val contentColor = remember(isSelected) {
        if (isSelected) colorScheme.onSurface
        else colorScheme.onSurface.copy(alpha = 0.5f)
    }

    BaseListItem(
        modifier = modifier
            .clickable(enabled = isSelected.not(), onClick = onClick),
        leading = {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor
            )
        },
        content = {
            Text(
                text = item.text,
                color = contentColor
            )
        }
    )
}
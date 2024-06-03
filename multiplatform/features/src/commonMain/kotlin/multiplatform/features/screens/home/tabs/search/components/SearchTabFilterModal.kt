package multiplatform.features.screens.home.tabs.search.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchTabFilterModal(
    state: SheetState,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        sheetState = state,
        content = {
            Text("Hello World")
        },
        onDismissRequest = onDismiss
    )
}
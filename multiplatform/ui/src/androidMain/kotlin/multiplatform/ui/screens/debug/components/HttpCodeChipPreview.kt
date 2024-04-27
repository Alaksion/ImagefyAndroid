package multiplatform.ui.screens.debug.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import multiplatform.ui.design.theme.ImagefyTheme
import multiplatform.ui.design.tokens.ImagefySpacing

@Composable
@Preview(showBackground = true)
private fun Preview() {
    ImagefyTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(ImagefySpacing.Medium)
        ) {
            HttpCodeTag(
                code = 500,
            )
            HttpCodeTag(
                code = 400,
            )
            HttpCodeTag(
                code = 300,
            )
            HttpCodeTag(
                code = 200,
            )
            HttpCodeTag(
                code = 100,
            )
        }

    }
}
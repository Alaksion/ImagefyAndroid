package multiplatform.ui.utils

import android.content.Intent
import android.net.Uri

actual fun requestBrowser(
    url: String,
    contextWrapper: AppContextWrapper
) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setData(Uri.parse(url))
    contextWrapper.context.startActivity(intent)
}
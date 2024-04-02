package multiplatform.ui.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun requestBrowser(
    url: String,
    contextWrapper: AppContextWrapper
) {
    UIApplication.sharedApplication.openURL(url = NSURL(string = url))
}
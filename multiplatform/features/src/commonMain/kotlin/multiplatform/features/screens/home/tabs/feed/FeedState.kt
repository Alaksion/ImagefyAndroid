package multiplatform.features.screens.home.tabs.feed

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CalendarViewWeek
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhoto
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhotoOrderBy
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class FeedState(
    val photos: ImmutableList<ListPhoto> = persistentListOf(),
    val orderBy: ListPhotoOrderBy = ListPhotoOrderBy.Latest,
    val isNextPageLoading: Boolean = false,
    val isUserLogged: Boolean = false,
)

internal val ListPhotoOrderBy.icon: ImageVector
    get() {
        return when (this) {
            ListPhotoOrderBy.Latest -> Icons.Outlined.CalendarToday
            ListPhotoOrderBy.Oldest -> Icons.Outlined.CalendarViewWeek
            ListPhotoOrderBy.Popular -> Icons.Outlined.FavoriteBorder
        }
    }

internal val ListPhotoOrderBy.text: String
    get() {
        return when (this) {
            ListPhotoOrderBy.Latest -> "Latest"
            ListPhotoOrderBy.Oldest -> "Oldest"
            ListPhotoOrderBy.Popular -> "Popular"
        }
    }
package multiplatform.ui.screens.home.tabs.search

import io.github.alaksion.unsplashwrapper.api.models.parameters.PhotoOrientation
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosColor
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosContentFilter
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosItem
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosOrderBy
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.search.SearchPhotosParameters
import io.github.alaksion.unsplashwrapper.api.repositories.UnsplashSearchRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.foundation.logger.AppLogger
import multiplatform.stateScreenmodel.StateScreenModel

internal data class SearchTabState(
    val query: String = "",
    val page: Int = 0,
    val orderBy: SearchPhotosOrderBy = SearchPhotosOrderBy.Relevant,
    val contentFilter: SearchPhotosContentFilter = SearchPhotosContentFilter.Low,
    val color: SearchPhotosColor? = null,
    val orientation: PhotoOrientation? = null,
    val photos: ImmutableList<SearchPhotosItem> = persistentListOf(),
    val isLoadingNextPage: Boolean = false,
)

internal class SearchTabScreenModel(
    private val searchRepository: UnsplashSearchRepository,
    dispatcher: CoroutineDispatcher,
    logger: AppLogger,
) : StateScreenModel<SearchTabState>(
    dispatcher = dispatcher,
    initialState = SearchTabState(),
    logger = logger
) {

    private var currentPage = 0
    private var isInitialized = false

    fun search(force: Boolean) {
        if (isInitialized && force.not()) return
        setState(
            block = {
                val fetchPhotos = getPhotos()
                this.copy(
                    photos = fetchPhotos.results
                )
            }
        )
        isInitialized = true
    }

    fun updateQuery(query: String) = setState(
        block = {
            copy(query = query)
        }
    )

    fun loadNextPage() {
        if (currentData.isLoadingNextPage || isInitialized.not()) return
        updateState(
            block = {
                currentPage++
                update {
                    it.copy(isLoadingNextPage = true)
                }
                val nextPage = getPhotos().results
                update {
                    it.copy(
                        photos = (it.photos + nextPage).toPersistentList(),
                        isLoadingNextPage = false
                    )
                }
            }
        )
    }

    private suspend fun getPhotos() = searchRepository.searchPhotos(
        parameters = SearchPhotosParameters(
            query = currentData.query,
            page = currentPage,
            orderBy = currentData.orderBy,
            color = currentData.color,
            orientation = currentData.orientation,
            contentFilter = currentData.contentFilter
        )
    )

}
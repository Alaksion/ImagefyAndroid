package multiplatform.ui.screens.home.tabs.feed

import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhoto
import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhotoOrderBy
import io.github.alaksion.unsplashwrapper.api.repositories.UnsplashPhotosRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.StateScreenModel

private const val PAGE_LIMIT = 10



internal class FeedScreenModel(
    dispatcher: CoroutineDispatcher,
    private val photosRepository: UnsplashPhotosRepository,
) : StateScreenModel<FeedState>(
    dispatcher = dispatcher,
    initialState = FeedState()
) {
    private var isInitialized = false
    private var currentPage = 1

    fun initialize() {
        if (isInitialized) return
        setState(
            block = {
                val photos = photosRepository.getPhotos(
                    page = currentPage,
                    resultsPerPage = PAGE_LIMIT,
                    orderBy = this.orderBy
                )
                this.copy(
                    photos = (this.photos + photos).toPersistentList()
                )
            },
        )
        isInitialized = true
    }

    fun loadNextPage() {
        if (isInitialized.not() || currentData.showLoadingIndicator) return
        updateState(
            block = {
                currentPage++
                update { it.copy(showLoadingIndicator = true) }
                val newPhotos = photosRepository.getPhotos(
                    page = 0,
                    resultsPerPage = PAGE_LIMIT,
                    orderBy = currentData.orderBy
                )
                update { oldState ->
                    oldState.copy(
                        showLoadingIndicator = false,
                        photos = (currentData.photos + newPhotos).toPersistentList()
                    )
                }
            },
            showLoading = false
        )
    }

    fun updateOrderBy(
        value: ListPhotoOrderBy
    ) {
        updateState(
            block = {
                currentPage = 1
                update { oldState ->
                    oldState.copy(
                        orderBy = value
                    )
                }
                val reloadedFeed = photosRepository.getPhotos(
                    page = currentPage,
                    resultsPerPage = PAGE_LIMIT,
                    orderBy = currentData.orderBy
                )
                update { oldState ->
                    oldState.copy(
                        photos = reloadedFeed
                    )
                }
            },
        )
    }

}
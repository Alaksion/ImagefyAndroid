package multiplatform.ui.screens.home.tabs.feed

import io.github.alaksion.unsplashwrapper.api.models.photo.domain.list.ListPhotoOrderBy
import io.github.alaksion.unsplashwrapper.api.repositories.UnsplashPhotosRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import multiplatform.stateScreenmodel.StateScreenModel
import mutiplatform.session.SessionHandler

private const val PAGE_LIMIT = 10


internal class FeedScreenModel(
    dispatcher: CoroutineDispatcher,
    private val photosRepository: UnsplashPhotosRepository,
    private val sessionHandler: SessionHandler,
) : StateScreenModel<FeedState>(
    dispatcher = dispatcher,
    initialState = FeedState()
) {
    private var isDataLoaded = false
    private var currentPage = 1

    fun resumeState() {
        updateUserLoggedState()
        loadData()
    }

    fun loadNextPage() {
        if (isDataLoaded.not() || currentData.isNextPageLoading) return
        updateState(
            block = {
                currentPage++
                update { it.copy(isNextPageLoading = true) }
                val newPhotos = photosRepository.getPhotos(
                    page = currentPage,
                    resultsPerPage = PAGE_LIMIT,
                    orderBy = currentData.orderBy
                )
                update { oldState ->
                    oldState.copy(
                        isNextPageLoading = false,
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

    fun favoritePost(
        isFavorite: Boolean,
        photoId: String,
    ) {
        runCatching(
            showLoading = false,
            block = {
                if (isFavorite) photosRepository.unlikePhoto(photoId)
                else photosRepository.likePhoto(photoId)
            }
        )
    }

    private fun loadData() {
        if (isDataLoaded) return
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
        isDataLoaded = true
    }

    private fun updateUserLoggedState() {
        setState(
            block = {
                this.copy(isUserLogged = sessionHandler.currentUser != null)
            },
            showLoading = false
        )
    }

}
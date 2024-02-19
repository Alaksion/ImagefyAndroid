package io.github.alaksion.imagefy.features.home

import io.github.alaksion.stateviewmodel.StateScreenModel
import io.github.alaksion.unsplashwrapper.api.photos.domain.domain.models.listphotos.ListPhoto
import io.github.alaksion.unsplashwrapper.api.photos.domain.domain.models.listphotos.ListPhotoOrderBy
import io.github.alaksion.unsplashwrapper.api.photos.domain.repository.UnsplashPhotosRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher

private const val PAGE_LIMIT = 10

internal data class HomeState(
    val photos: ImmutableList<ListPhoto> = persistentListOf(),
    val orderBy: ListPhotoOrderBy = ListPhotoOrderBy.Latest,
    val showLoadingIndicator: Boolean = false,
)

internal class HomeScreenModel(
    dispatcher: CoroutineDispatcher,
    private val photosRepository: UnsplashPhotosRepository,
) : StateScreenModel<HomeState>(
    dispatcher = dispatcher,
    initialState = HomeState()
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

}
package io.github.alaksion.imagefy.di

import io.github.alaksion.unsplashwrapper.api.photos.domain.repository.UnsplashPhotosRepository
import io.github.alaksion.unsplashwrapper.api.search.domain.repository.UnsplashSearchRepository
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val unsplashModule = DI.Module("app-unsplash-module") {

    bindSingleton<UnsplashWrapperSdk> { UnsplashWrapperSdk.Instance }

    bindSingleton<UnsplashSearchRepository> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.searchRepository
    }

    bindSingleton<UnsplashPhotosRepository> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.photosRepository
    }

}
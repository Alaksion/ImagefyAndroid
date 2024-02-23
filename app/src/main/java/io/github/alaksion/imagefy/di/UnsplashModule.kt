package io.github.alaksion.imagefy.di

import io.github.alaksion.unsplashwrapper.api.currentuser.domain.UnsplashCurrentUserRepository
import io.github.alaksion.unsplashwrapper.api.photos.domain.repository.UnsplashPhotosRepository
import io.github.alaksion.unsplashwrapper.api.search.domain.repository.UnsplashSearchRepository
import io.github.alaksion.unsplashwrapper.platform.authentication.UnsplashAuth
import io.github.alaksion.unsplashwrapper.sdk.UnsplashWrapperSdk
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val unsplashModule = DI.Module("app-unsplash-module") {

    bindSingleton<UnsplashWrapperSdk> { UnsplashWrapperSdk.Instance }

    bindSingleton<UnsplashAuth> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.auth
    }

    bindSingleton<UnsplashSearchRepository> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.searchRepository
    }

    bindSingleton<UnsplashPhotosRepository> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.photosRepository
    }

    bindSingleton<UnsplashCurrentUserRepository> {
        val sdk = instance<UnsplashWrapperSdk>()
        sdk.currentUserRepository
    }

}
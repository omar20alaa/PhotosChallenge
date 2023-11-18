package app.photos_challenge.app.di.module

import app.photos_challenge.app.presentation.viewmodel.PhotosViewModel
import app.photos_challenge.domain.usecase.FetchAndCachePhotosUseCase
import app.photos_challenge.domain.usecase.GetCachedPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun providePhotosViewModel(
        fetchAndCachePhotosUseCase: FetchAndCachePhotosUseCase,
        getCachedPhotosUseCase: GetCachedPhotosUseCase
    ): PhotosViewModel {
        return PhotosViewModel(fetchAndCachePhotosUseCase, getCachedPhotosUseCase)
    }

}
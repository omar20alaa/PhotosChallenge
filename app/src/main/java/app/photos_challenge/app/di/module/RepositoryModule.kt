package app.photos_challenge.app.di.module


import app.photos_challenge.data.datasources.local.PhotosLocalDataSource
import app.photos_challenge.data.datasources.local.PhotosLocalDataSourceImpl
import app.photos_challenge.data.datasources.remote.PhotosRemoteDataSource
import app.photos_challenge.data.datasources.remote.PhotosRemoteDataSourceImpl
import app.photos_challenge.data.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(
        remoteDataSource: PhotosRemoteDataSource,
        localDataSource: PhotosLocalDataSource
    ): PhotosRepository {
        return PhotosRepository(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun providePhotosLocalDataSource(localDataSourceImpl: PhotosLocalDataSourceImpl): PhotosLocalDataSource {
        return localDataSourceImpl
    }

    @Provides
    @Singleton
    fun providePhotosRemoteDataSource(remoteDataSourceImpl: PhotosRemoteDataSourceImpl): PhotosRemoteDataSource {
        return remoteDataSourceImpl
    }
}
package app.photos_challenge.app.di.module


import app.photos_challenge.data.datasources.PhotosLocalDataSource
import app.photos_challenge.data.datasources.PhotosLocalDataSourceImpl
import app.photos_challenge.data.datasources.PhotosRemoteDataSource
import app.photos_challenge.data.datasources.PhotosRemoteDataSourceImpl
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
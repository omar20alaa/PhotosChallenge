package app.photos_challenge.app.di.module

import app.photos_challenge.data.repository.PhotosMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providePhotosMapper(): PhotosMapper {
        return PhotosMapper()
    }
}
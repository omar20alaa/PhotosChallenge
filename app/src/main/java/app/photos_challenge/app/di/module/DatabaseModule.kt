package app.photos_challenge.app.di.module

import android.content.Context
import androidx.room.Room
import app.photos_challenge.data.database.PhotosDatabase
import app.photos_challenge.data.database.PhotosDao
import app.photos_challenge.app.utils.AppConstants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PhotosDatabase {
        return Room.databaseBuilder(
            context,
            PhotosDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFlickrPhotoDao(database: PhotosDatabase): PhotosDao {
        return database.photoDao()
    }

}
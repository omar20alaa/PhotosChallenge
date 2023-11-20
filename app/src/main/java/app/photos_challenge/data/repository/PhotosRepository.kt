package app.photos_challenge.data.repository

import app.photos_challenge.data.datasources.local.PhotosLocalDataSource
import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.datasources.remote.PhotosRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val remoteDataSource: PhotosRemoteDataSource,
    private val localDataSource: PhotosLocalDataSource
) {

    suspend fun fetchPhotos(page: Int): Result<List<FlickrPhoto>> {
        return try {
            remoteDataSource.fetchPhotos(page)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun cachePhotos(photos: List<FlickrPhoto>): Result<Unit> {
        return try {
            localDataSource.cachePhotos(photos)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getCachedPhotos(): Flow<List<FlickrPhoto>> {
        return localDataSource.getCachedPhotos()
    }

}
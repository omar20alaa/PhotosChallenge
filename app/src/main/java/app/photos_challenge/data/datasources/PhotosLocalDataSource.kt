package app.photos_challenge.data.datasources

import app.photos_challenge.data.models.FlickrPhoto
import kotlinx.coroutines.flow.Flow

interface PhotosLocalDataSource {
    suspend fun cachePhotos(photos: List<FlickrPhoto>)
    fun getCachedPhotos(): Flow<List<FlickrPhoto>>
}
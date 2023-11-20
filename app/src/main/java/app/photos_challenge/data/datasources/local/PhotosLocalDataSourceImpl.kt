package app.photos_challenge.data.datasources.local

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.database.PhotosDao
import app.photos_challenge.data.repository.PhotosMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotosLocalDataSourceImpl @Inject constructor(
    private val photoDao: PhotosDao,
    private val mapper: PhotosMapper
) : PhotosLocalDataSource {
    override suspend fun cachePhotos(photos: List<FlickrPhoto>) {
        val photoEntities = mapper.mapToEntityModel(photos)
        photoDao.insertPhotos(photoEntities)
    }
    override fun getCachedPhotos(): Flow<List<FlickrPhoto>> {
        return photoDao.getAllPhotos()
            .map { photoEntities ->
                mapper.mapToDomainModel(photoEntities)
            }
    }
}
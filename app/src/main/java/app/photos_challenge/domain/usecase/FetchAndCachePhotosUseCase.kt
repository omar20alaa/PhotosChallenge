package app.photos_challenge.domain.usecase

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.repository.PhotosRepository
import javax.inject.Inject

class FetchAndCachePhotosUseCase @Inject constructor(private val repository: PhotosRepository) {

    suspend operator fun invoke(page: Int): Result<List<FlickrPhoto>> {
        val result = repository.fetchPhotos(page)
        if (result.isSuccess) {
            val photos = result.getOrDefault(emptyList())
            repository.cachePhotos(photos)
        }
        return result
    }
}
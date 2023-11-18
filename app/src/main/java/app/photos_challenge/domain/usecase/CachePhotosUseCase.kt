package app.photos_challenge.domain.usecase

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.repository.PhotosRepository
import javax.inject.Inject

class CachePhotosUseCase @Inject constructor(private val repository: PhotosRepository) {

    suspend operator fun invoke(photos: List<FlickrPhoto>) {
        repository.cachePhotos(photos)
    }

}
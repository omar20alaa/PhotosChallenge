package app.photos_challenge.domain.usecase

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.repository.PhotosRepository
import javax.inject.Inject

class FetchPhotosUseCase @Inject constructor(private val repository: PhotosRepository) {

    suspend operator fun invoke(page: Int): Result<List<FlickrPhoto>> {
        return repository.fetchPhotos(page)
    }
}
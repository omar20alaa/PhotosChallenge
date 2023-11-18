package app.photos_challenge.domain.usecase

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.data.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCachedPhotosUseCase @Inject constructor(private val repository: PhotosRepository) {

    fun invoke(): Flow<List<FlickrPhoto>> {
        return repository.getCachedPhotos()
    }

}
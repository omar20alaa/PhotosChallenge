package app.photos_challenge.data.datasources.remote

import app.photos_challenge.data.models.FlickrPhoto


interface PhotosRemoteDataSource {
    suspend fun fetchPhotos(page: Int): Result<List<FlickrPhoto>>
}
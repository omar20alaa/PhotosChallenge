package app.photos_challenge.data.datasources.remote

import app.photos_challenge.data.models.FlickrPhoto
import app.photos_challenge.app.network.Api
import app.photos_challenge.app.utils.AppConstants.Companion.API_KEY
import app.photos_challenge.app.utils.AppConstants.Companion.COLOR
import app.photos_challenge.app.utils.AppConstants.Companion.FORMAT
import app.photos_challenge.app.utils.AppConstants.Companion.METHOD
import app.photos_challenge.app.utils.AppConstants.Companion.NO_JSON_CALL_BACK
import app.photos_challenge.app.utils.AppConstants.Companion.PER_PAGE

import java.io.IOException
import javax.inject.Inject

class PhotosRemoteDataSourceImpl @Inject constructor(private val api: Api) :
    PhotosRemoteDataSource {
    override suspend fun fetchPhotos(page: Int): Result<List<FlickrPhoto>> {
        return try {
            val response =
                api.getPhotos(
                    METHOD,
                    FORMAT,
                    COLOR,
                    NO_JSON_CALL_BACK,
                    PER_PAGE,
                    API_KEY,
                    page
                )
            if (response.isSuccessful) {
                val photos = response.body()?.photos?.photoList ?: emptyList()
                Result.success(photos)
            } else {
                val errorMessage =
                    "Failed to fetch photos. Error code: ${response.code()}, message: ${response.message()}"
                Result.failure(IOException(errorMessage))
            }
        } catch (ioException: IOException) {
            Result.failure(ioException)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
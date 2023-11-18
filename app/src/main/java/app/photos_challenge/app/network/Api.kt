package app.photos_challenge.app.network


import app.photos_challenge.data.models.PhotoResponse
import app.photos_challenge.app.utils.AppConstants.Companion.COLOR
import app.photos_challenge.app.utils.AppConstants.Companion.FORMAT
import app.photos_challenge.app.utils.AppConstants.Companion.METHOD
import app.photos_challenge.app.utils.AppConstants.Companion.NO_JSON_CALL_BACK
import app.photos_challenge.app.utils.AppConstants.Companion.PER_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("method") method: String = METHOD,
        @Query("format") format: String = FORMAT,
        @Query("text") text: String = COLOR,
        @Query("nojsoncallback") noJsonCallback: Int = NO_JSON_CALL_BACK,
        @Query("per_page") perPage: Int = PER_PAGE,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<PhotoResponse>

}
package app.photos_challenge.data.models

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val photos: PhotoData,
    val stat: String
)
data class PhotoData(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val total: Int,
    @SerializedName("photo") val photoList: List<FlickrPhoto>,
)
data class FlickrPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    @SerializedName("ispublic") val isPublic: Int,
    @SerializedName("isfriend") val isFriend: Int,
    @SerializedName("isfamily") val isFamily: Int
){
    val imageUrl: String
        get() = "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
}
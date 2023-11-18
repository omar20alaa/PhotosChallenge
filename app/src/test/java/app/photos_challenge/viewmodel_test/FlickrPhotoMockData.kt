package app.photos_challenge.viewmodel_test

import app.photos_challenge.data.models.FlickrPhoto

object FlickrPhotoMockData {
    fun getMockFlickrPhotos(): List<FlickrPhoto> {
        return listOf(
            FlickrPhoto(
                id = "1",
                owner = "Owner 1",
                secret = "Secret 1",
                server = "Server 1",
                farm = 1,
                title = "Title 1",
                isPublic = 10,
                isFriend = 10,
                isFamily = 10,
            ),
            FlickrPhoto(
                id = "2",
                owner = "Owner 2",
                secret = "Secret 2",
                server = "Server 2",
                farm = 2,
                title = "Title 2",
                isPublic = 10,
                isFriend = 10,
                isFamily = 10,
            ),
        )
    }
}
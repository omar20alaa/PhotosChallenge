package app.photos_challenge.data.repository

import app.photos_challenge.data.database.PhotosEntity
import app.photos_challenge.data.models.FlickrPhoto
import javax.inject.Inject

class PhotosMapper @Inject constructor() {

    fun mapToDomainModel(entities: List<PhotosEntity>): List<FlickrPhoto> {
        return entities.map { entity ->
            mapToDomainModel(entity)
        }
    }

    private fun mapToDomainModel(entity: PhotosEntity): FlickrPhoto {
        return FlickrPhoto(
            id = entity.id,
            owner = entity.owner,
            secret = entity.secret,
            server = entity.server,
            farm = entity.farm,
            title = entity.title,
            isPublic = entity.ispublic,
            isFriend = entity.isfriend,
            isFamily = entity.isfamily,
        )
    }

    fun mapToEntityModel(photos: List<FlickrPhoto>): List<PhotosEntity> {
        return photos.map { photo ->
            mapToEntityModel(photo)
        }
    }

    private fun mapToEntityModel(photo: FlickrPhoto): PhotosEntity {
        return PhotosEntity(
            id = photo.id,
            owner = photo.owner,
            secret = photo.secret,
            server = photo.server,
            farm = photo.farm,
            title = photo.title,
            ispublic = photo.isPublic,
            isfriend = photo.isFriend,
            isfamily = photo.isFamily,
            imageUrl = photo.imageUrl
        )
    }
}
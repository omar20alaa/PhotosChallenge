package app.photos_challenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.photos_challenge.app.utils.AppConstants.Companion.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class PhotosEntity(
    @PrimaryKey val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
    val imageUrl: String
)
package app.photos_challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotosEntity::class], version = 1, exportSchema = true)
abstract class PhotosDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotosDao

}
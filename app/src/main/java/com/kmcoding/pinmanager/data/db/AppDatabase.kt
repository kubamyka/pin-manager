package com.kmcoding.pinmanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kmcoding.pinmanager.domain.Pin

@Database(entities = [Pin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pinDao(): PinDao
}

package com.kmcoding.pinmanager.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kmcoding.pinmanager.domain.model.Pin
import kotlinx.coroutines.flow.Flow

@Dao
interface PinDao {
    @Query("SELECT * FROM pins ORDER BY LOWER(name) ASC")
    fun getAllPins(): Flow<List<Pin>>

    @Query("SELECT * FROM pins WHERE LOWER(name) = LOWER(:name)")
    fun getPinByName(name: String): Flow<Pin?>

    @Query("SELECT * FROM pins WHERE code = :code")
    fun getPinByCode(code: String): Flow<Pin?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pin: Pin)

    @Update
    suspend fun update(pin: Pin)

    @Delete
    suspend fun delete(pin: Pin)
}

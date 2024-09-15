package com.kmcoding.pinmanager.domain.repository

import com.kmcoding.pinmanager.domain.Pin
import kotlinx.coroutines.flow.Flow

interface PinRepository {
    fun getAllPins(): Flow<List<Pin>>

    suspend fun getPin(name: String): Flow<Pin?>

    suspend fun insertPin(pin: Pin)

    suspend fun updatePin(pin: Pin)

    suspend fun deletePin(pin: Pin)
}

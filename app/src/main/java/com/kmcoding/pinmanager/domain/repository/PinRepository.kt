package com.kmcoding.pinmanager.domain.repository

import com.kmcoding.pinmanager.domain.Pin
import kotlinx.coroutines.flow.Flow

interface PinRepository {
    suspend fun getAllPins(): Flow<List<Pin>>

    suspend fun getPin(id: Int): Flow<Pin>

    suspend fun insertPin(pin: Pin)

    suspend fun updatePin(pin: Pin)

    suspend fun deletePin(pin: Pin)
}

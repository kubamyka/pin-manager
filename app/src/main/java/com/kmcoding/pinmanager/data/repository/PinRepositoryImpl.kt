package com.kmcoding.pinmanager.data.repository

import com.kmcoding.pinmanager.data.db.PinDao
import com.kmcoding.pinmanager.domain.Pin
import com.kmcoding.pinmanager.domain.repository.PinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PinRepositoryImpl
    @Inject
    constructor(
        private val pinDao: PinDao,
    ) : PinRepository {
        override fun getAllPins(): Flow<List<Pin>> = pinDao.getAllPins()

        override suspend fun getPin(name: String): Flow<Pin?> = pinDao.getPin(name = name)

        override suspend fun insertPin(pin: Pin) {
            pinDao.insert(pin = pin)
        }

        override suspend fun updatePin(pin: Pin) {
            pinDao.update(pin = pin)
        }

        override suspend fun deletePin(pin: Pin) {
            pinDao.delete(pin = pin)
        }
    }

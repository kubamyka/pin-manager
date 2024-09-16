package com.kmcoding.pinmanager.fake

import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.data.source.FakeDataSource.getFakePinByCode
import com.kmcoding.pinmanager.data.source.FakeDataSource.getFakePinByName
import com.kmcoding.pinmanager.domain.model.Pin
import com.kmcoding.pinmanager.domain.repository.PinRepository
import kotlinx.coroutines.flow.flow

class FakePinRepositoryImpl : PinRepository {
    override fun getAllPins() = flow { emit(fakePins) }

    override suspend fun getPinByName(name: String) =
        flow {
            emit(getFakePinByName(name))
        }

    override suspend fun getPinByCode(code: String) =
        flow {
            emit(getFakePinByCode(code))
        }

    override suspend fun insertPin(pin: Pin) {}

    override suspend fun updatePin(pin: Pin) {}

    override suspend fun deletePin(pin: Pin) {}
}

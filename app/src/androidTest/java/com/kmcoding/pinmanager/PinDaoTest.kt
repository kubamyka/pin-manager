package com.kmcoding.pinmanager

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kmcoding.pinmanager.data.db.AppDatabase
import com.kmcoding.pinmanager.data.db.PinDao
import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.domain.model.Pin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PinDaoTest {
    private lateinit var pinDao: PinDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun createDatabase() {
        val context: Context = ApplicationProvider.getApplicationContext()
        appDatabase =
            Room
                .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        pinDao = appDatabase.pinDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllPins_returnsAllPinsFromDatabase() =
        runBlocking {
            addTwoPinsToDatabase()
            val allPins = pinDao.getAllPins().first()
            assertEquals(allPins[0], fakePins[0])
            assertEquals(allPins[1], fakePins[1])
        }

    @Test
    @Throws(Exception::class)
    fun daoGetPinByName_returnsPinFromDatabase() =
        runBlocking {
            addOnePinToDatabase()
            val pin = pinDao.getPinByName(fakePins[0].name)
            assertEquals(pin.first(), fakePins[0])
        }

    @Test
    @Throws(Exception::class)
    fun daoGetPinByCode_returnsPinFromDatabase() =
        runBlocking {
            addOnePinToDatabase()
            val pin = pinDao.getPinByCode(fakePins[0].code)
            assertEquals(pin.first(), fakePins[0])
        }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsPinIntoDatabase() =
        runBlocking {
            addOnePinToDatabase()
            val allPins = pinDao.getAllPins().first()
            assertEquals(allPins[0], fakePins[0])
        }

    @Test
    @Throws(Exception::class)
    fun daoUpdatePins_updatesPinsInDatabase() =
        runBlocking {
            val pinToUpdate1 = Pin(id = 1, name = "Anna", code = "111111")
            val pinToUpdate2 = Pin(id = 2, name = "George", code = "222222")

            addTwoPinsToDatabase()
            pinDao.update(pinToUpdate1)
            pinDao.update(pinToUpdate2)

            val allPins = pinDao.getAllPins().first()
            assertEquals(allPins[0], pinToUpdate1)
            assertEquals(allPins[1], pinToUpdate2)
        }

    @Test
    @Throws(Exception::class)
    fun daoDeletePins_deletesAllPinsFromDatabase() =
        runBlocking {
            addTwoPinsToDatabase()
            pinDao.delete(fakePins[0])
            pinDao.delete(fakePins[1])
            val allPins = pinDao.getAllPins().first()
            assertTrue(allPins.isEmpty())
        }

    private suspend fun addOnePinToDatabase() {
        pinDao.insert(fakePins[0])
    }

    private suspend fun addTwoPinsToDatabase() {
        pinDao.insert(fakePins[0])
        pinDao.insert(fakePins[1])
    }
}

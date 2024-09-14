package com.kmcoding.pinmanager.di

import android.content.Context
import androidx.room.Room
import com.kmcoding.pinmanager.data.db.AppDatabase
import com.kmcoding.pinmanager.data.db.PinDao
import com.kmcoding.pinmanager.data.repository.PinRepositoryImpl
import com.kmcoding.pinmanager.domain.repository.PinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext
        context: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePinDao(appDatabase: AppDatabase): PinDao = appDatabase.pinDao()

    @Singleton
    @Provides
    fun providePinRepository(pinDao: PinDao): PinRepository = PinRepositoryImpl(pinDao = pinDao)
}

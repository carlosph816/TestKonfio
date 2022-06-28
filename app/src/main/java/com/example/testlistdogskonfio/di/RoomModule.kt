package com.example.testlistdogskonfio.di

import android.content.Context
import androidx.room.Room
import com.example.testlistdogskonfio.data.database.DogsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DOGS_DATABASE_NAME = "dogs_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogsDatabase::class.java, DOGS_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDogsDao(db: DogsDatabase) = db.getQuoteDao()
}
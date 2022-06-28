package com.example.testlistdogskonfio.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testlistdogskonfio.data.database.dao.DogsDao
import com.example.testlistdogskonfio.data.database.entities.DogEntity

@Database(entities = [DogEntity::class], version = 1)
abstract class DogsDatabase: RoomDatabase() {

    abstract fun getQuoteDao(): DogsDao
}
package com.example.testlistdogskonfio.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testlistdogskonfio.data.database.entities.DogEntity
@Dao
interface DogsDao {

    @Query("SELECT * FROM dogs_table")
    suspend fun getAllDogs():List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<DogEntity>)

}
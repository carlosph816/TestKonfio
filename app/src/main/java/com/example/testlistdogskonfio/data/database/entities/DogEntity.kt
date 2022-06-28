package com.example.testlistdogskonfio.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testlistdogskonfio.data.model.response.Dog

@Entity(tableName = "dogs_table")
data class DogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "dogName") val dogName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "image") val image: String,
)


fun Dog.toDatabase() = DogEntity(dogName = dogName, description = description, age = age, image = image)

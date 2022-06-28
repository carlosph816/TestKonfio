package com.example.testlistdogskonfio.data.model.response

import com.example.testlistdogskonfio.data.database.entities.DogEntity
import com.example.testlistdogskonfio.data.model.DogModel
import com.google.gson.annotations.SerializedName

data class Dog (
    @SerializedName("dogName")
    val dogName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: Int = 0,
    @SerializedName("image")
    val image: String
)


fun DogModel.toDomain() = Dog(dogName,description,age, image)
fun DogEntity.toDomain() = Dog(dogName,description,age, image)
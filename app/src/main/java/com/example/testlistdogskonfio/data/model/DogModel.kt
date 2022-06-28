package com.example.testlistdogskonfio.data.model

import com.google.gson.annotations.SerializedName

data class DogModel (
    @SerializedName("dogName")
    val dogName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: Int = 0,
    @SerializedName("image")
    val image: String
)
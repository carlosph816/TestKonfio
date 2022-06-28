package com.example.testlistdogskonfio.data.net

import com.example.testlistdogskonfio.data.model.response.Dog
import retrofit2.Response
import retrofit2.http.GET

interface ListDogsService {
    @GET("945366962796773376")
    suspend fun getListDogs() : Response<ArrayList<Dog>>
}
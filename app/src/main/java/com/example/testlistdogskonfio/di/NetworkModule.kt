package com.example.testlistdogskonfio.di

import com.example.testlistdogskonfio.data.net.ListDogsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Provides
        fun provideRetrofit(retrofit: Retrofit.Builder): ListDogsService =
            retrofit
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ListDogsService::class.java)
    }
}
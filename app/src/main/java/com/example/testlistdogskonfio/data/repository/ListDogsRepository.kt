package com.example.testlistdogskonfio.data.repository

import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.model.ListDogsModel
import kotlinx.coroutines.flow.Flow

interface ListDogsRepository {
    suspend fun getListDogs() : Flow<DataState<ListDogsModel>>
}
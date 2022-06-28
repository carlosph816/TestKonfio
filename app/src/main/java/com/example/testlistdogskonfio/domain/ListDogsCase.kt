package com.example.testlistdogskonfio.domain

import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.model.ListDogsModel
import kotlinx.coroutines.flow.Flow

interface ListDogsCase {
    suspend fun getListDogs() : Flow<DataState<ListDogsModel>>
}
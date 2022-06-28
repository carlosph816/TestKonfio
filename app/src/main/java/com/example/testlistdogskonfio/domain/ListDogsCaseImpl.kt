package com.example.testlistdogskonfio.domain

import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.data.repository.ListDogsRepositoryImpl
import com.example.testlistdogskonfio.utils.PrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListDogsCaseImpl @Inject constructor(private val listDogsRepository : ListDogsRepositoryImpl, private val preferences: PrefManager): ListDogsCase{

    override suspend fun getListDogs(): Flow<DataState<ListDogsModel>>  = flow {
        if(preferences.isFirstTimeLaunch){
            listDogsRepository.getListDogs()
                .catch { e-> e.printStackTrace()}
                .collect { state ->
                    emit(state)
                }
        }else{
            listDogsRepository.getAllDogsFromDatabase()
                .catch { e-> e.printStackTrace()}
                .collect { state ->
                    emit(state)
                }
        }
    }.flowOn(Dispatchers.IO)
}
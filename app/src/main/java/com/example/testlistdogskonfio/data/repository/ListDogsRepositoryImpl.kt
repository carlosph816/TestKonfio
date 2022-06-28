package com.example.testlistdogskonfio.data.repository

import android.content.Context
import com.example.testlistdogskonfio.R
import com.example.testlistdogskonfio.common.BaseError
import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.database.dao.DogsDao
import com.example.testlistdogskonfio.data.database.entities.DogEntity
import com.example.testlistdogskonfio.data.database.entities.toDatabase
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.data.model.response.Dog
import com.example.testlistdogskonfio.data.model.response.DogsResponse
import com.example.testlistdogskonfio.data.model.response.toDomain
import com.example.testlistdogskonfio.data.net.ListDogsService
import com.example.testlistdogskonfio.data.net.mapper.ListDogsMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListDogsRepositoryImpl @Inject constructor(private val service: ListDogsService,private val dogsDao: DogsDao, @ApplicationContext private val context: Context
):ListDogsRepository {

    private val listDogsMapper: ListDogsMapper by lazy { ListDogsMapper() }

    override suspend fun getListDogs(): Flow<DataState<ListDogsModel>>  = flow  {
        emit(DataState.Loading)
        try {
            val response = service.getListDogs()
            if (response.isSuccessful) {
                response.body()?.let {list->
                    when(response.code()) {
                        200 -> {
                            if(list.isNotEmpty()){
                                val responseDogs = DogsResponse()
                                responseDogs.listDogs = list
                                insertDogs(responseDogs.listDogs.map { it.toDatabase() })
                                emit(DataState.Success(data = listDogsMapper.mapFromEntity(entity = responseDogs)))
                            }else{
                                emit(DataState.Error(error = BaseError(cause =context.getString(R.string.error_service_1), code =response.code())))
                            }
                        }
                        else -> emit(DataState.Error(error = BaseError(cause =response.message(), code =response.code())))
                    }
                }?: emit(DataState.Error(error = BaseError(cause = context.getString(R.string.error_service_2))))
            }else{
                emit(DataState.Error(error = BaseError(cause = context.getString(R.string.error_service_3))))
            }
        }catch (e: Exception) {
            emit(DataState.Error(error = BaseError(cause = context.getString(R.string.error_service_4), exception = e)))
        }
    }

    suspend fun getAllDogsFromDatabase():Flow<DataState<ListDogsModel>> = flow{
        val response: List<DogEntity> = dogsDao.getAllDogs()
        response.map { it.toDomain() }
        val arrayDogs: ArrayList<Dog> = ArrayList()
        for(i in response){
            arrayDogs.add(Dog(i.dogName,i.description,i.age,i.image))
        }
        val responseDogs = DogsResponse()
        responseDogs.listDogs = arrayDogs
        emit(DataState.Success(data = listDogsMapper.mapFromEntity(entity = responseDogs)))
    }

    private suspend fun insertDogs(quotes:List<DogEntity>){
        dogsDao.insertAll(quotes)
    }
}
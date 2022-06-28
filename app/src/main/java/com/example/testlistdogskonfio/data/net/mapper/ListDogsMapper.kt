package com.example.testlistdogskonfio.data.net.mapper

import com.example.testlistdogskonfio.common.EntityMapper
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.data.model.response.DogsResponse

class ListDogsMapper : EntityMapper<DogsResponse,ListDogsModel> {

    override fun mapFromEntity(entity: DogsResponse):  ListDogsModel =
        ListDogsModel(
            listDogs = entity.listDogs
        )
}
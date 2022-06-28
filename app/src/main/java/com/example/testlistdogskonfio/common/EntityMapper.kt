package com.example.testlistdogskonfio.common

interface EntityMapper <Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    {
        throw UnsupportedOperationException()
    }
}
package com.plcoding.composepaging3caching.network.model.mapper

interface EntityMapper<Entity, Domain> {
    fun asEntity(domain: Domain):Entity
    fun asDomain(entity: Entity):Domain
}
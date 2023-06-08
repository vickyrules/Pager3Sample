package com.plcoding.composepaging3caching.network.model.mapper

import com.plcoding.composepaging3caching.data.local.BeerEntity
import com.plcoding.composepaging3caching.network.model.Beer

object BeerMapper: EntityMapper<List<BeerEntity>, List<Beer>> {
    override fun asEntity(domain: List<Beer>): List<BeerEntity> {
        return domain.map {bear->
            BeerEntity(
                id = bear.id,
                name = bear.name,
                tagline = bear.tagline,
                description = bear.description,
                firstBrewed = bear.first_brewed,
                imageUrl = bear.image_url
            )
        }
    }

    override fun asDomain(entity: List<BeerEntity>): List<Beer> {

        return entity.map {entity->
            Beer(
                id = entity.id,
                name = entity.name,
                tagline = entity.tagline,
                description = entity.description,
                first_brewed = entity.firstBrewed,
                image_url = entity.imageUrl
            )
        }

    }

}

fun List<BeerEntity>.asDomain():List<Beer>{
    return BeerMapper.asDomain(this)
}

fun List<Beer>.asEntity():List<BeerEntity>{
    return BeerMapper.asEntity(this)
}
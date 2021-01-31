package com.cagataykolus.itunessearchapp.data.mapper

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
interface MapperEntity<Entity, DomainModel> {
    fun mapFromNetworkEntity(entity: Entity): DomainModel
    fun mapToNetworkEntity(domainModel: DomainModel): Entity
}
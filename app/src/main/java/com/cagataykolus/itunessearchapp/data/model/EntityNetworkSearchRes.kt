package com.cagataykolus.itunessearchapp.data.model

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
data class EntityNetworkSearchRes(
    var resultCount: Int,
    var results: List<EntityNetworkContent>
) : EntityModel
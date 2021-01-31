package com.cagataykolus.itunessearchapp.domain.model

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
enum class Kind(val value: String) {

    UNKNOWN("");

    companion object {
        private val map = values().associateBy(Kind::value)
        fun fromString(type: String?) = type?.let { map[type] } ?: UNKNOWN
    }
}

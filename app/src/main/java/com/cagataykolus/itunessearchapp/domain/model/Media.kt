package com.cagataykolus.itunessearchapp.domain.model

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
enum class Media(val value: String) {

    MOVIE("movie"),
    MUSIC("music"),
    SOFTWARE("software"),
    EBOOK("ebook"),
    ALL("all");

    override fun toString(): String {
        return value
    }

    companion object {
        fun fromString(text: String): Media {
            val textToMedia = values().find {
                it.value.compareTo(text, true) == 0
            }
            return textToMedia ?: ALL
        }
    }
}
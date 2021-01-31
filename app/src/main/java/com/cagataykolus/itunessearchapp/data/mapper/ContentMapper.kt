package com.cagataykolus.itunessearchapp.data.mapper

import com.cagataykolus.itunessearchapp.data.model.EntityModel
import com.cagataykolus.itunessearchapp.data.model.EntityNetworkContent
import com.cagataykolus.itunessearchapp.data.model.EntityNetworkSearchRes
import com.cagataykolus.itunessearchapp.domain.model.Content
import com.cagataykolus.itunessearchapp.domain.model.Kind
import com.cagataykolus.itunessearchapp.domain.model.WrapperType
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
class ContentMapper
@Inject
constructor() : MapperEntity<EntityModel, Content> {
    override fun mapFromNetworkEntity(entityModel: EntityModel): Content {

        val entityNetworkContent = entityModel as EntityNetworkContent

        return Content(
            id = null,
            wrapperType = WrapperType.fromString(entityNetworkContent.wrapperType),
            kind = Kind.fromString(entityNetworkContent.kind),
            collectionId = entityNetworkContent.collectionId,
            trackId = entityNetworkContent.trackId,
            artistName = entityNetworkContent.artistName,
            collectionName = entityNetworkContent.collectionName,
            trackName = entityNetworkContent.trackName,
            collectionCensoredName = entityNetworkContent.collectionCensoredName,
            trackCensoredName = entityNetworkContent.trackCensoredName,
            collectionArtistId = entityNetworkContent.collectionArtistId,
            collectionArtistViewUrl = entityNetworkContent.collectionArtistViewUrl,
            collectionViewUrl = entityNetworkContent.collectionViewUrl,
            trackViewUrl = entityNetworkContent.trackViewUrl,
            previewUrl = entityNetworkContent.previewUrl,
            artworkUrl30 = entityNetworkContent.artworkUrl30,
            artworkUrl60 = entityNetworkContent.artworkUrl60,
            artworkUrl100 = entityNetworkContent.artworkUrl100,
            collectionPrice = entityNetworkContent.collectionPrice,
            trackPrice = entityNetworkContent.trackPrice,
            trackRentalPrice = entityNetworkContent.trackRentalPrice,
            collectionHdPrice = entityNetworkContent.collectionHdPrice,
            trackHdPrice = entityNetworkContent.trackHdPrice,
            trackHdRentalPrice = entityNetworkContent.trackHdRentalPrice,
            releaseDate = entityNetworkContent.releaseDate,
            collectionExplicitness = entityNetworkContent.collectionExplicitness,
            trackExplicitness = entityNetworkContent.trackExplicitness,
            discCount = entityNetworkContent.discCount,
            discNumber = entityNetworkContent.discNumber,
            trackCount = entityNetworkContent.trackCount,
            trackNumber = entityNetworkContent.trackNumber,
            trackTimeMillis = entityNetworkContent.trackTimeMillis,
            country = entityNetworkContent.country,
            currency = entityNetworkContent.currency,
            primaryGenreName = entityNetworkContent.primaryGenreName,
            contentAdvisoryRating = entityNetworkContent.contentAdvisoryRating,
            shortDescription = entityNetworkContent.shortDescription,
            longDescription = entityNetworkContent.longDescription,
            hasITunesExtras = entityNetworkContent.hasITunesExtras
        )
    }

    fun mapFromNetworkEntitySearchRes(entityNetworkSearchRes: EntityNetworkSearchRes): List<Content> {

        return entityNetworkSearchRes.results.map {
            val entityNetworkContent = it
            Content(
                id = null,
                wrapperType = WrapperType.fromString(entityNetworkContent.wrapperType),
                kind = Kind.fromString(entityNetworkContent.kind),
                collectionId = entityNetworkContent.collectionId,
                trackId = entityNetworkContent.trackId,
                artistName = entityNetworkContent.artistName,
                collectionName = entityNetworkContent.collectionName,
                trackName = entityNetworkContent.trackName,
                collectionCensoredName = entityNetworkContent.collectionCensoredName,
                trackCensoredName = entityNetworkContent.trackCensoredName,
                collectionArtistId = entityNetworkContent.collectionArtistId,
                collectionArtistViewUrl = entityNetworkContent.collectionArtistViewUrl,
                collectionViewUrl = entityNetworkContent.collectionViewUrl,
                trackViewUrl = entityNetworkContent.trackViewUrl,
                previewUrl = entityNetworkContent.previewUrl,
                artworkUrl30 = entityNetworkContent.artworkUrl30,
                artworkUrl60 = entityNetworkContent.artworkUrl60,
                artworkUrl100 = entityNetworkContent.artworkUrl100,
                collectionPrice = entityNetworkContent.collectionPrice,
                trackPrice = entityNetworkContent.trackPrice,
                trackRentalPrice = entityNetworkContent.trackRentalPrice,
                collectionHdPrice = entityNetworkContent.collectionHdPrice,
                trackHdPrice = entityNetworkContent.trackHdPrice,
                trackHdRentalPrice = entityNetworkContent.trackHdRentalPrice,
                releaseDate = entityNetworkContent.releaseDate,
                collectionExplicitness = entityNetworkContent.collectionExplicitness,
                trackExplicitness = entityNetworkContent.trackExplicitness,
                discCount = entityNetworkContent.discCount,
                discNumber = entityNetworkContent.discNumber,
                trackCount = entityNetworkContent.trackCount,
                trackNumber = entityNetworkContent.trackNumber,
                trackTimeMillis = entityNetworkContent.trackTimeMillis,
                country = entityNetworkContent.country,
                currency = entityNetworkContent.currency,
                primaryGenreName = entityNetworkContent.primaryGenreName,
                contentAdvisoryRating = entityNetworkContent.contentAdvisoryRating,
                shortDescription = entityNetworkContent.shortDescription,
                longDescription = entityNetworkContent.longDescription,
                hasITunesExtras = entityNetworkContent.hasITunesExtras
            )
        }
    }

    override fun mapToNetworkEntity(domainModel: Content): EntityModel {

        return object : EntityModel {}
    }
}

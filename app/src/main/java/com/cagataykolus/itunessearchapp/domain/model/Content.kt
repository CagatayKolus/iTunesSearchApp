package com.cagataykolus.itunessearchapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@Parcelize
data class Content(
    var id: Int?,
    var wrapperType: WrapperType?,
    var kind: Kind?,
    var collectionId: Int?,
    var trackId: Int?,
    var artistName: String?,
    var collectionName: String?,
    var trackName: String?,
    var collectionCensoredName: String?,
    var trackCensoredName: String?,
    var collectionArtistId: Int?,
    var collectionArtistViewUrl: String?,
    var collectionViewUrl: String?,
    var trackViewUrl: String?,
    var previewUrl: String?,
    var artworkUrl30: String?,
    var artworkUrl60: String?,
    var artworkUrl100: String?,
    var collectionPrice: Float?,
    var trackPrice: Float?,
    var trackRentalPrice: Float?,
    var collectionHdPrice: Float?,
    var trackHdPrice: Float?,
    var trackHdRentalPrice: Float?,
    var releaseDate: String?,
    var collectionExplicitness: String?,
    var trackExplicitness: String?,
    var discCount: Int?,
    var discNumber: Int?,
    var trackCount: Int?,
    var trackNumber: Int?,
    var trackTimeMillis: Int?,
    var country: String?,
    var currency: String?,
    var primaryGenreName: String?,
    var contentAdvisoryRating: String?,
    var shortDescription: String?,
    var longDescription: String?,
    var hasITunesExtras: Boolean?
) : DomainModel, Parcelable

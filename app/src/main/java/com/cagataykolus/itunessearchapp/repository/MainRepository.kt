package com.cagataykolus.itunessearchapp.repository

import android.content.Context
import com.cagataykolus.itunessearchapp.BuildConfig
import com.cagataykolus.itunessearchapp.R
import com.cagataykolus.itunessearchapp.data.mapper.ContentMapper
import com.cagataykolus.itunessearchapp.data.model.EntityNetworkSearchRes
import com.cagataykolus.itunessearchapp.data.network.ApiServiceRetrofit
import com.cagataykolus.itunessearchapp.data.network.ResultWrapper
import com.cagataykolus.itunessearchapp.data.network.safeApiCall
import com.cagataykolus.itunessearchapp.domain.model.Content
import com.cagataykolus.itunessearchapp.domain.model.Media
import com.cagataykolus.itunessearchapp.domain.state.DataState
import com.cagataykolus.itunessearchapp.domain.state.DataState.*
import com.cagataykolus.itunessearchapp.domain.state.MessageType
import com.cagataykolus.itunessearchapp.domain.state.StateMessage
import com.cagataykolus.itunessearchapp.presentation.home.MainDataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
class MainRepository
constructor(
    private val appContext: Context,
    private val apiService: ApiServiceRetrofit,
    private val contentMapper: ContentMapper
) {

    /**
     * Return the list of Contents from ITunes Search API.
     * @param term The text to search in ITunes Search API.
     * @param media The type of Media to fetch.
     */
    suspend fun getContents(
        term: String,
        media: Media
    ): Flow<DataState<MainDataState>> = flow {
        try {
            emit(LOADING(true))

            val response = safeApiCall {
                apiService.get(BuildConfig.DEFAULT_COUNTRY_CODE, term, media)
            }

            when (response) {
                is ResultWrapper.Success<EntityNetworkSearchRes> -> {

                    val networkData = response.value
                    val contentsNetwork: List<Content> = networkData.let {
                        contentMapper.mapFromNetworkEntitySearchRes(it)
                    }

                    emit(SUCCESS(MainDataState(contentsNetwork)))
                }
                else -> {
                    // Emit error with message if network issue or error response
                    val errorTxt = appContext.getString(R.string.error_network)
                    val stateMsg = StateMessage(errorTxt, MessageType.ERROR)

                    emit(ERROR(stateMsg))
                }
            }

            emit(LOADING(false))

        } catch (e: Exception) {
            Timber.e(e)
            val errorTxt = appContext.getString(R.string.error_message)
            val stateMsg = StateMessage(errorTxt, MessageType.ERROR)
            emit(ERROR(stateMsg))
        }
    }
}

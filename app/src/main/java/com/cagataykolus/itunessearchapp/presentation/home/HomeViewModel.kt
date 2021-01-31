package com.cagataykolus.itunessearchapp.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.itunessearchapp.domain.model.Content
import com.cagataykolus.itunessearchapp.domain.model.Media
import com.cagataykolus.itunessearchapp.domain.state.DataState
import com.cagataykolus.itunessearchapp.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
class HomeViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<MainDataState>> = MutableLiveData()

    val dataState: LiveData<DataState<MainDataState>>
        get() = _dataState

    fun setStateEvent(intent: MainIntent) {

        viewModelScope.launch {

            when (intent) {
                is MainIntent.GetContentsIntent -> {

                    val termSearch = intent.term ?: ""
                    val mediaType = intent.media ?: Media.ALL

                    mainRepository.getContents(termSearch, mediaType)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainIntent.None -> {
                }
            }
        }
    }
}

sealed class MainIntent {
    data class GetContentsIntent(val term: String?, val media: Media?) : MainIntent()
    object None : MainIntent()
}

class MainDataState(
    val contents: List<Content>
)

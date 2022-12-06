package com.example.vk_education

import GifSourse
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_education.Model.Gif
import com.example.vk_education.Network.GifApi
import com.example.vk_education.Provider.GifProvider
import kotlinx.coroutines.flow.Flow


class GifViewModel : ViewModel() {
    val gif: Flow<PagingData<Gif>> = Pager(PagingConfig(pageSize = 25)) {
        GifSourse()
    }.flow.cachedIn(viewModelScope)

}
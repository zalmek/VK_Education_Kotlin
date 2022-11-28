package com.example.vk_education

import androidx.lifecycle.ViewModel
import com.example.vk_education.Network.GifApi
import com.example.vk_education.Provider.GifProvider


class GifViewModel : ViewModel() {
    suspend fun getGifs() = GifProvider(GifApi.create()).getGifs("gZ3s6VZmS8vgIrMc1yPvEg12k3Yzdo1P","bat")
}
package com.example.vk_education.Provider

import com.example.vk_education.Model.Gif
import com.example.vk_education.Network.GifApi

class GifProvider (val api: GifApi) {
    suspend fun getGifs(api_key:String, q:String,): List<Gif>{
        return api.getGifs(api_key,q).data
    }
}

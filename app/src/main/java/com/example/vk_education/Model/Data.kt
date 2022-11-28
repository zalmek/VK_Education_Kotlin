package com.example.vk_education.Model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data") val data: List<Gif>
)

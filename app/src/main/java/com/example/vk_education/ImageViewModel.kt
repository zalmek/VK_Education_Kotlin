package com.example.vk_education

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class ImageViewModel : ViewModel() {
    var images1 = mutableListOf(Image(0)).toMutableStateList()
    val images2 = mutableListOf(Image(0)).toMutableStateList()
    val images3 = mutableListOf(Image(0)).toMutableStateList()
    val images4 = mutableListOf(Image(0)).toMutableStateList()
}
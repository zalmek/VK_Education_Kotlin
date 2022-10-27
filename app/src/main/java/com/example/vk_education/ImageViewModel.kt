package com.example.vk_education

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class ImageViewModel : ViewModel() {
    var images1 = mutableListOf<Image>(Image(0)).toMutableStateList()
    val images2 = mutableListOf<Image>(Image(0)).toMutableStateList()
    val images3 = mutableListOf<Image>(Image(0)).toMutableStateList()
    val images4 = mutableListOf<Image>(Image(0)).toMutableStateList()
}
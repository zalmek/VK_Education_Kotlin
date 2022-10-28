package com.example.vk_education

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class NumberViewModel : ViewModel() {
    var numbers = mutableListOf(Number(1)).toMutableStateList()
}
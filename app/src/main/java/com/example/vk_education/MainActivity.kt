package com.example.vk_education

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

var list1 = mutableListOf<Image>(Image(0))
var list2 = mutableListOf<Image>(Image(0))
var list3 = mutableListOf<Image>(Image(0))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeView()
        }


    }
}

@Preview
@Composable
fun ComposeView() {
    Row {
        val lista = remember { list1.toMutableStateList() }
        ImageListContent(list = lista, onClick1 = {
            lista.add(Image(list1.size))
            list1 = lista
        })
        val listb = remember { list2.toMutableStateList() }
        ImageListContent(list = listb, onClick1 = {
            listb.add(Image(list2.size))
            list2 = listb
        })
        val listc = remember { list3.toMutableStateList() }
        ImageListContent(list = listc, onClick1 = {
            listc.add(Image(list3.size))
            list3 = listc
        })
    }
}

@Preview
@Composable
fun ImageScreen() {
    val list = remember { list1.toMutableStateList() }
    ImageListContent(list = list, onClick1 = {
        list.add(Image(list1.size))
        list1 = list
    })
}


@Composable
fun ImageListContent(list: List<Image>, onClick1: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .height(750.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            // val images :MutableList<Image> by remember { mutableStateOf(provider.imagelist) }
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = list,
                    itemContent = {
                        ImageListItem(image = it)
                    })
            }
        }
        Row(
            modifier = Modifier
                .height(790.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = onClick1) {
                Text(text = "Add")
            }
        }
    }
}
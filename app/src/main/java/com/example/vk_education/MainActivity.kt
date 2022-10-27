package com.example.vk_education

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeScreen(resources.configuration.orientation)
        }


    }
}
@Composable
fun ComposeScreen(orientation: Int, viewModel: ImageViewModel = viewModel(), modifier: Modifier=Modifier){
    var modifier1 = Modifier.fillMaxHeight(0.90f)
    if (orientation==Configuration.ORIENTATION_LANDSCAPE){
        modifier1=Modifier.fillMaxHeight(0.8f).padding(start = 40.dp, end = 40.dp)
    }
    Row() {
        ImageListContent(list = viewModel.images1, onClick1 = {Unit.apply {viewModel.images1.add(Image(viewModel.images1.size))}},modifier1)
        ImageListContent(list = viewModel.images2, onClick1 = {Unit.apply {viewModel.images2.add(Image(viewModel.images2.size))}},modifier1)
        ImageListContent(list = viewModel.images3, onClick1 = {Unit.apply {viewModel.images3.add(Image(viewModel.images3.size))}},modifier1)
        if (orientation==Configuration.ORIENTATION_LANDSCAPE){
            ImageListContent(list = viewModel.images4, onClick1 = {Unit.apply {viewModel.images4.add(Image(viewModel.images4.size))}},modifier1)
        }
    }

}
@Composable
fun ImageListContent(list: List<Image>, onClick1: () -> Unit, modifier: Modifier=Modifier) {
    Column() {
        Row(
            modifier =Modifier.align(Alignment.CenterHorizontally).then(modifier)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = list,
                    itemContent = {
                        ImageListItem(image = it,modifier)
                    })
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = onClick1, modifier = Modifier.requiredHeight(40.dp)) {
                Text(text = "Add")
            }
        }
    }
}
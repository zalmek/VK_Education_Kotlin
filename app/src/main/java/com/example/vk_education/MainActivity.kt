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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun ComposeScreen(
    orientation: Int,
    viewModel: NumberViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var modifier1 = Modifier.fillMaxHeight(0.90f)
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        modifier1 = Modifier
            .fillMaxHeight(0.8f)
            .padding(start = 40.dp, end = 40.dp)
    }
    Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Row {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                for(i in 0..2){
                    ImageListContent(
                        list = viewModel.numbers.filterIndexed { index, number -> number.number % 3 == i },
                        modifier1
                    )
                }
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                for(i in 0..3){
                    ImageListContent(
                        list = viewModel.numbers.filterIndexed { index, number -> number.number % 4 == i },
                        modifier1
                    )
                }
            }
        }
        Button(
                onClick = { Unit.apply { viewModel.numbers.add(Number(viewModel.numbers.size)) } },
                modifier = Modifier
                    .height(
                        50.dp
                    )
                    .align(CenterHorizontally)
                    .wrapContentWidth(CenterHorizontally)
                    .wrapContentHeight()
            ) {
                Text(text = "Add number")
            }

    }

}

@Composable
fun ImageListContent(list: List<Number>, modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .then(modifier)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = list,
                    itemContent = {
                        ImageListItem(number = it, modifier)
                    })
            }
        }
    }
}
package com.example.vk_education

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vk_education.Model.Gif
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    var size: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            size = savedInstanceState.getInt("ourValue")
        }

        setContent {
            val viewModel: GifViewModel = viewModel<GifViewModel>()
            ComposeScreen(resources.configuration.orientation, viewModel, size)
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("ourValue", size)
        super.onSaveInstanceState(outState)
    }
}

@Composable
fun ComposeGrid(columns: Int, viewModel: GifViewModel, modifier: Modifier = Modifier) {
    var list by remember {
        mutableStateOf(listOf<Gif>())
    }
    if (list.isEmpty())
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24), contentDescription = "No", modifier = Modifier.clickable { viewModel.viewModelScope.launch(Dispatchers.IO+ CoroutineExceptionHandler{ _, throwable ->
            throwable.printStackTrace()
        }){
            list = viewModel.getGifs()
        } }.fillMaxSize())
    else
    LazyVerticalGrid(columns = GridCells.Fixed(columns), modifier = modifier) {
        viewModel.viewModelScope.launch(Dispatchers.IO+ CoroutineExceptionHandler{ _, throwable ->
            throwable.printStackTrace()
        }) {
            list = viewModel.getGifs()
        }
        items(items = list) { item: Gif ->
            ImageListItem(gif = item)
        }
    }
}

@Composable
fun ComposeScreen(
    orientation: Int,
    viewModel: GifViewModel = viewModel(),
    initialSize: Int,
    modifier: Modifier = Modifier,
) {
    //var change1 = false
    val changed = remember {
        mutableStateOf(false)
    }
    var modifier1 = Modifier.fillMaxHeight(0.90f)
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        modifier1 = Modifier
            .fillMaxHeight(0.8f)
            .padding(start = 40.dp, end = 40.dp)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Row {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                ComposeGrid(columns = 3, viewModel = viewModel, modifier1)
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ComposeGrid(columns = 4, viewModel = viewModel, modifier1)
            }
        }
    }

}

package com.example.vk_education

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.vk_education.Model.Gif

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: GifViewModel = viewModel<GifViewModel>()
            ComposeScreen(orientation = resources.configuration.orientation, viewModel = viewModel)
        }
    }


}


@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main",
    orientation: Int,
    viewModel: GifViewModel = viewModel(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("main") {
            ComposeScreen(
                orientation = orientation,
            )
        }
    }
}


@Composable
fun ComposeGrid(
    columns: Int,
    viewModel: GifViewModel,
    saveGif: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val gifListItems: LazyPagingItems<Gif> = viewModel.gif.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        items(items = gifListItems) { item ->
            Box(
                Modifier
                    .background(Color.DarkGray)
                    .clickable
                    {
                        if (item != null) {
                            saveGif(item.id)
                        }
                    }
            ){
                ImageListItem(gif = item!!.id)
            }
        }
    }
//    LazyVerticalGrid(columns = GridCells.Fixed(columns), modifier = modifier) {
//        items(items = gifListItems) { item: Gif ->
//            ImageListItem(gif = item)
//        }
//    }

    gifListItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
            }
            loadState.append is LoadState.Loading -> {

            }
            loadState.append is LoadState.Error || itemCount == 0 -> {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            refresh()
                        })
            }
        }
    }
}

@Composable
fun ComposeScreen(
    orientation: Int,
    viewModel: GifViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {


    //var change1 = false
    var gifId: String? by remember {
        mutableStateOf(null)
    }

    fun savegif(gif: String) {
        gifId = gif
    }
    fun resetgif() {
        gifId = null
    }

    var modifier1 = Modifier.fillMaxHeight(0.90f)
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        modifier1 = Modifier
            .fillMaxHeight(0.8f)
            .padding(start = 40.dp, end = 40.dp)
    }
    if (gifId != null) {
        ImageScreen(gif = gifId!!,::resetgif)
    } else {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()) {
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    ComposeGrid(columns = 3, viewModel = viewModel, ::savegif, modifier1)
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ComposeGrid(columns = 4, viewModel = viewModel, ::savegif, modifier1)
                }
            }
        }
    }
}

@Composable
fun ImageScreen(
    gif: String,
    resetGif: ()->Unit,
    modifier: Modifier = Modifier.clickable { resetGif() }
) {
    ImageListItem(gif = gif,modifier = Modifier.clickable { resetGif() })
}
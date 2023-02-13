package com.example.vk_education

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageListItem(gif: String, modifier: Modifier= Modifier) {

    val context = LocalView.current.context
    val circularProgressDrawable = remember(context) {
        CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
    }
        GlideImage(
            model = String.format(
                "https://media3.giphy.com/media/%s/giphy.gif?cid=afe55739p8qr2uvjzz1onniq0lp371vj1o338jz2h5ohru06&rid=giphy.gif&ct=g",
                gif
            ),
            contentDescription = "No description",
            modifier = modifier.then(
                Modifier
                    .padding(8.dp)
                    .fillMaxSize())
        )
        {
            it.error((R.drawable.ic_baseline_error_24)).placeholder(circularProgressDrawable)
        }

}





















package com.example.vk_education

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent


@Composable
fun ImageListItem(image: Image) {
    Card(
        modifier = Modifier
            .background(color = if (image.number%2==1) Color.Red else Color.Cyan).padding(horizontal = 8.dp, vertical = 8.dp)
         ,
        shape = RoundedCornerShape(corner = CornerSize(4.dp))
    )
    {
        Row {
            Column(
                modifier = Modifier
                    .background(color = if (image.number%2==1) Color.Red else Color.Cyan)
                    .padding(16.dp)
                    .width(45.dp)
                    .height(45.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),text = image.number.toString(), style = typography.headlineSmall,)
            }
        }
    }
}
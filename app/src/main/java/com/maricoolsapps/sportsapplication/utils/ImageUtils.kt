package com.maricoolsapps.sportsapplication.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.maricoolsapps.sportsapplication.ui.composables.DEFAULT_NEWS_IMAGE
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LoadPicture(
    url: String,
    @DrawableRes defaultImg: Int,
    modifier: Modifier
) {

    GlideImage(
        modifier = modifier,
        imageModel = url,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MovieCard(
    url: String?,
    text: String?,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick)
            .background(color = Color.Black)
            .width(110.dp),
        elevation = 8.dp
    ) {
        Column {
            LoadPicture(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                url = "${Constants.BASE_POSTER_URL}${url}",
                defaultImg = DEFAULT_NEWS_IMAGE
            )
            text?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                    style = MaterialTheme.typography.h4
                )
            }
        }
    }
}

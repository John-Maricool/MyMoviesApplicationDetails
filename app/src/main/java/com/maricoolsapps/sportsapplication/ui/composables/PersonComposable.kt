package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maricoolsapps.sportsapplication.data.models.Image
import com.maricoolsapps.sportsapplication.utils.Constants
import com.maricoolsapps.sportsapplication.utils.loadPicture

@Composable
fun BiographyComposable(title: String, body: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Text(text = body, color = Color.White, fontSize = 14.sp)
    }
}


@Composable
fun PicsRowListComposable(
    casts: List<Image>
) {

    Column {
        Text(text = "Images")
        LazyRow {
            items(casts.size) { index ->
                val item = casts[index]
                item.let {
                    val image =
                        loadPicture(
                            url = "${Constants.BASE_BACKDROP_URL}${it.filePath}",
                            defaultImg = DEFAULT_NEWS_IMAGE
                        ).value
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            modifier = Modifier
                                .width(100.dp)
                                .height(150.dp)
                                .padding(4.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "Image"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PersonDetailsHeaderComposable(img: String?, name: String, role: String) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card(
            shape = MaterialTheme.shapes.large,
            elevation = 11.dp
        ) {
            val image =
                loadPicture(
                    url = "${Constants.BASE_BACKDROP_URL}${img}",
                    defaultImg = DEFAULT_NEWS_IMAGE
                ).value
            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image"
                )
            }
        }
        Text(text = name, color = Color.White, fontSize = 25.sp)
        Text(text = role, color = Color.White, fontSize = 14.sp)
    }

}
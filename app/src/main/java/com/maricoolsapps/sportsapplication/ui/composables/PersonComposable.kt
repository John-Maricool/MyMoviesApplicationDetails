package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maricoolsapps.sportsapplication.data.models.Credit
import com.maricoolsapps.sportsapplication.data.models.Image
import com.maricoolsapps.sportsapplication.utils.Constants
import com.maricoolsapps.sportsapplication.utils.LoadPicture
import com.maricoolsapps.sportsapplication.utils.MovieCard

@Composable
fun BiographyComposable(title: String, body: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        Text(
            text = body, style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun PicsRowListComposable(
    casts: List<Image>
) {

    Column {
        LazyRow {
            items(casts.size) { index ->
                val item = casts[index]
                LoadPicture(
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(4.dp),
                    url = "${Constants.BASE_BACKDROP_URL}${item.filePath}",
                    defaultImg = DEFAULT_NEWS_IMAGE
                )
            }
        }
    }
}

@Composable
fun CreditsRowListComposable(
    casts: List<Credit>,
    onClick: (id: Int) -> Unit
) {

    Column {
        LazyRow {
            items(casts.size) { index ->
                val item = casts[index]

                MovieCard(url = item.posterPath, text = item.title) {
                    onClick(item.id)
                }
            }
        }
    }
}

@Composable
fun PersonDetailsHeaderComposable(
    img: String?, name: String, role: String
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card(
            shape = CircleShape,
            elevation = 11.dp
        ) {
            LoadPicture(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp),
                url = "${Constants.BASE_BACKDROP_URL}${img}",
                defaultImg = DEFAULT_NEWS_IMAGE
            )
        }
        Text(
            text = name, style = MaterialTheme.typography.h2
        )
        Text(
            text = role, style = MaterialTheme.typography.body1,
        )
    }

}







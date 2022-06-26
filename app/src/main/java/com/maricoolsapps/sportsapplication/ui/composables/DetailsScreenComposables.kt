package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.data.models.Cast
import com.maricoolsapps.sportsapplication.data.models.Genre
import com.maricoolsapps.sportsapplication.data.models.IconString
import com.maricoolsapps.sportsapplication.data.models.Video
import com.maricoolsapps.sportsapplication.utils.*

@Composable
fun VideoImageComposable(video: Video, onClick: (id: String) -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = { onClick(video.id) })
            .width(120.dp)
            .height(70.dp)
    ) {
        val image =
            loadPicture(
                url = "${Constants.BASE_YT_IMG_URL}${video.key}/hqdefault.jpg",
                defaultImg = DEFAULT_NEWS_IMAGE
            ).value
        image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(4.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "Image"
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_play_circle_outline_24),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun VideoRowListComposable(
    videos: List<Video>,
    onClick: (key: String) -> Unit
) {

    LazyRow {
        items(videos.size) { index ->
            val item = videos[index]
            item.let { video ->
                VideoImageComposable(video) {
                    onClick(video.key)
                }
            }
        }
    }
}


@Composable
fun MovieDetailsTitlePart(
    movieTitle: String,
    genre: List<Genre>?,
    votes: String?,
    rating: Float?,
    movieTime: Int?,
    movieDate: String?,
    lang: String?
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(
                text = movieTitle,
                fontSize = 35.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
            )
            Text(
                text = changeGenre(genre),
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            rating?.let {
                if (votes != null) {
                    RatingAndVotes(rateValue = it, votes = votes)
                }
            }
        }
        Column(modifier = Modifier.padding(9.dp)) {
            lang?.let {
                val icon = IconString(R.drawable.ic_baseline_language_24, getMovieLanguage(it))
                MovieDetailsTitleSecondPart(iconString = icon)
            }
            movieTime?.let {
                val icon2 =
                    IconString(R.drawable.ic_baseline_access_time_24, getMovieRuntime(it))
                MovieDetailsTitleSecondPart(iconString = icon2)
            }
            movieDate?.let {
                val icon3 = IconString(R.drawable.ic_baseline_event_24, it)
                MovieDetailsTitleSecondPart(iconString = icon3)
            }

        }
    }
}

@Composable
fun MovieImageBigComposable(imageUrl: String) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = 11.dp
    ) {
        val image =
            loadPicture(
                url = "${Constants.BASE_BACKDROP_URL}${imageUrl}",
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
}


@Composable
fun MovieDetailsTitleSecondPart(iconString: IconString) {
    Row(modifier = Modifier.padding(end = 8.dp, bottom = 2.dp)) {
        Icon(
            painter = painterResource(id = iconString.icon),
            contentDescription = null,
            modifier = Modifier.padding(end = 5.dp),
            tint = Color.White
        )
        Text(text = iconString.str, fontSize = 14.sp, color = Color.White)
    }
}

@Composable
fun CastRowListComposable(
    casts: List<Cast>,
    onClick: (id: Long) -> Unit
) {

    LazyRow {
        items(casts.size) { index ->
            val item = casts[index]
            item.let {
                MovieCard(item = castsToMovieListItem(it)) {
                    onClick(it.id.toLong())
                }
            }
        }
    }
}


@Composable
fun MovieDetailsComposable(details: String) {
    Text(
        text = details,
        fontSize = 14.sp,
        color = Color.White,
        modifier = Modifier.padding(bottom = 10.dp, start = 8.dp)
    )
}

fun changeGenre(genres: List<Genre>?): String {
    var str = ""
    genres?.forEach {
        str += "${it.name}/"
    }
    return str
}
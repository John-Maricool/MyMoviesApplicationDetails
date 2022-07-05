package com.maricoolsapps.sportsapplication.ui.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.maricoolsapps.sportsapplication.data.models.Genre
import com.maricoolsapps.sportsapplication.data.models.Movie
import com.maricoolsapps.sportsapplication.utils.Constants
import com.maricoolsapps.sportsapplication.utils.LoadPicture

@ExperimentalComposeUiApi
@Composable
fun HomeScreenSingleDetail(movie: Movie) {
    Column {
        movie.backdropPath?.let { HomeScreenMovieImageAndTitle(imageUrl = it, title = movie.title) }
        Spacer(modifier = Modifier.padding(10.dp))
        val res = 5 * (movie.voteAverage / 10f)
        RatingAndVotes(rateValue = res, votes = movie.voteCount.toString())
        Spacer(modifier = Modifier.padding(10.dp))
        Log.d("result", movie.toString())
        movie.genres?.let { HomeSingleDetailGenres(it) }
    }
}

@ExperimentalComposeUiApi
@Composable
fun HomeScreenMovieImageAndTitle(imageUrl: String, title: String) {
    ConstraintLayout(modifier = Modifier.height(240.dp)) {
        val (imageConst, text) = createRefs()
        LoadPicture(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(imageConst) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(240.dp),
            url = "${Constants.BASE_BACKDROP_URL}${imageUrl}",
            defaultImg = DEFAULT_NEWS_IMAGE
        )
        /*image?.let {
            Image(
                bitmap = it.asImageBitmap(),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(imageConst) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(240.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
            )*/
        //}
        Text(
            text = title,
            modifier = Modifier
                .constrainAs(text) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imageConst.start)
                    end.linkTo(imageConst.end)
                }
                .padding(start = 4.dp),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun RatingAndVotes(rateValue: Float, votes: String) {
    Row(modifier = Modifier.padding(start = 8.dp)) {
        RatingBar(
            value = rateValue,
            onValueChange = {},
            onRatingChanged = {},
            modifier = Modifier.padding(end = 3.dp),
            config = RatingBarConfig().size(15.dp).activeColor(Color.Red).inactiveColor(Color.DarkGray)
        )
        Text(
            text = "$votes votes",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(CenterVertically)
        )
    }
}

@Composable
fun HomeSingleDetailGenres(genre: List<Genre>) {
    Row(modifier = Modifier.padding(start = 8.dp)) {
        Text(
            text = genre[0].name,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .background(color = Color.Red, shape = Shapes().medium)
                .padding(start = 5.dp, end = 5.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = genre[1].name,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .background(color = Color.Black, shape = Shapes().medium)
                .padding(start = 5.dp, end = 5.dp)
        )
    }
}





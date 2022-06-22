package com.maricoolsapps.sportsapplication.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.maricoolsapps.sportsapplication.ui.MainViewModel
import com.maricoolsapps.sportsapplication.utils.BottomNavItem
import com.maricoolsapps.sportsapplication.utils.Constants
import com.maricoolsapps.sportsapplication.utils.Constants.IN_THEATRE_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.POPULAR_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.UPCOMING_MOVIES

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {
    viewModel.getFirstMovie()
    viewModel.getPopularMovies()
    viewModel.getInTheatreMovies()
    viewModel.getUpcomingMovies()
    Column(modifier = Modifier.background(Color.Black)) {
        HomeScreenSingleDetail(viewModel.singleMovie.value)
        Spacer(modifier = Modifier.padding(3.dp))
        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
                .padding(bottom = 45.dp)
        ) {
            item {
                MovieRowListWithHeader(movies = viewModel.popularMovieList, onClick = {
                    navController.navigate("${BottomNavItem.details.screen_route}/$it")
                }, name = "Popular") {
                    navController.navigate("${BottomNavItem.moviesType.screen_route}/$POPULAR_MOVIES")
                }
                MovieRowListWithHeader(movies = viewModel.inTheatreMovieList, onClick = {
                    navController.navigate("${BottomNavItem.details.screen_route}/$it")
                }, name = "In Theatre") {
                    navController.navigate("${BottomNavItem.moviesType.screen_route}/$IN_THEATRE_MOVIES")
                }
                MovieRowListWithHeader(movies = viewModel.upcomingMovieList, onClick = {
                    navController.navigate("${BottomNavItem.details.screen_route}/$it")
                }, name = "Upcoming") {
                    navController.navigate("${BottomNavItem.moviesType.screen_route}/$UPCOMING_MOVIES")
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MoviesScreen(viewModel: MainViewModel, navController: NavHostController) {
    viewModel.getAllMovies()
    MovieGridListComposable(movies = viewModel.allMovieList, onClick = {
        navController.navigate("${BottomNavItem.details.screen_route}/$it")
    })
}

@ExperimentalFoundationApi
@Composable
fun TvShowsScreen(viewModel: MainViewModel, navController: NavHostController) {
    viewModel.getTvShows()
    MovieGridListComposable(movies = viewModel.tvShowList, onClick = {
        navController.navigate("${BottomNavItem.details.screen_route}/$it")
    })
}

@Composable
fun DetailsScreen(viewModel: MainViewModel, navController: NavHostController, id: Long) {
    viewModel.getMovieDetail(id)
    viewModel.getCredits(id)
    viewModel.getVideos(id)
    val movie = viewModel.movie.value
    val context = LocalContext.current
    Column(modifier = Modifier.background(Color.Black)) {
        movie.backdropPath?.let { MovieImageBigComposable(imageUrl = it) }
        Spacer(modifier = Modifier.padding(4.dp))
        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
        ) {
            item {
                MovieDetailsTitlePart(
                    movieTitle = movie.title,
                    genre = movie.genres,
                    votes = movie.voteCount.toString(),
                    rating = movie.voteAverage,
                    movieDate = movie.releaseDate!!,
                    movieTime = movie.runtime.toString(),
                    lang = movie.originalLanguage!!
                )
                Spacer(modifier = Modifier.padding(4.dp))
                movie.overview?.let { MovieDetailsComposable(details = it) }
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "VIDEOS",
                    color = Color.White,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                VideoRowListComposable(videos = viewModel.videos.value, onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW, Uri.parse("${Constants.BASE_YT_WATCH_URL}${it}")
                    )
                    context.startActivity(intent)
                })
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "CAST",
                    color = Color.White,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.padding(2.dp))
                CastRowListComposable(casts = viewModel.casts.value, onClick = {})
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieTypeScreen(viewModel: MainViewModel, navController: NavHostController, type: Int) {
    when (type) {
        POPULAR_MOVIES -> {
            MovieGridListComposable(movies = viewModel.popularMovieList, onClick = {
                navController.navigate("${BottomNavItem.details.screen_route}/$it")
            })
        }
        IN_THEATRE_MOVIES -> {
            MovieGridListComposable(movies = viewModel.inTheatreMovieList, onClick = {
                navController.navigate("${BottomNavItem.details.screen_route}/$it")
            })
        }
        UPCOMING_MOVIES -> {
            MovieGridListComposable(movies = viewModel.upcomingMovieList, onClick = {
                navController.navigate("${BottomNavItem.details.screen_route}/$it")
            })
        }
    }
}
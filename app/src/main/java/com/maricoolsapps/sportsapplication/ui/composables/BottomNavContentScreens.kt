package com.maricoolsapps.sportsapplication.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.data.models.IconString
import com.maricoolsapps.sportsapplication.ui.view_models.*
import com.maricoolsapps.sportsapplication.utils.*
import com.maricoolsapps.sportsapplication.utils.Constants.IN_THEATRE_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.POPULAR_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.UPCOMING_MOVIES

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    viewModel.loadData()

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AppBar(title = "Home", navController = navController)
        ShowContent(isConnected = viewModel.isConnected) {
            val isDisplayed = viewModel.isLoading
            CircularIndeterminateProgressBar(isDisplayed = isDisplayed.value)
            HomeScreenSingleDetail(viewModel.singleMovie.value)
            Spacer(modifier = Modifier.padding(3.dp))

            LazyColumn(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(bottom = 45.dp)
            ) {
                item {
                    MovieRowListWithHeader(movies = viewModel.popularMovieList.value, onClick = {
                        navController.navigate("${BottomNavItem.details.screen_route}/$it")
                    }, name = "Popular") {
                        navController.navigate("${BottomNavItem.moviesType.screen_route}/$POPULAR_MOVIES")
                    }
                    MovieRowListWithHeader(movies = viewModel.inTheatreMovieList.value, onClick = {
                        navController.navigate("${BottomNavItem.details.screen_route}/$it")
                    }, name = "In Theatre") {
                        navController.navigate("${BottomNavItem.moviesType.screen_route}/$IN_THEATRE_MOVIES")
                    }
                    MovieRowListWithHeader(movies = viewModel.upcomingMovieList.value, onClick = {
                        navController.navigate("${BottomNavItem.details.screen_route}/$it")
                    }, name = "Upcoming") {
                        navController.navigate("${BottomNavItem.moviesType.screen_route}/$UPCOMING_MOVIES")
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MoviesScreen(
    viewModel: MoviesListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    viewModel.loadData()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AppBar(title = "Movies", navController = navController)
        ShowContent(isConnected = viewModel.isConnected) {
            MovieGridListComposable(movies = viewModel.allMovieList, onClick = {
                navController.navigate("${BottomNavItem.details.screen_route}/$it")
            })
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TvShowsScreen(
    viewModel: TvShowsListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    viewModel.loadData()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AppBar(title = "TV shows", navController = navController)
        ShowContent(isConnected = viewModel.isConnected) {
            MovieGridListComposable(movies = viewModel.tvShowList, onClick = {
                navController.navigate("${BottomNavItem.tvDetails.screen_route}/$it")
            })
        }
    }
}

@Composable
fun DetailsScreen(
    viewModel: MovieDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController,
    id: Long
) {
    viewModel.loadData(id)
    val movie = viewModel.movie.value
    val isDisplayed = viewModel.isLoading
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        ShowContent(isConnected = viewModel.isConnected) {
            CircularIndeterminateProgressBar(isDisplayed = isDisplayed.value)
            AppBar(title = movie.title, navController = navController)
            movie.backdropPath?.let { MovieImageBigComposable(imageUrl = it) }
            Spacer(modifier = Modifier.padding(4.dp))
            LazyColumn(
                modifier = Modifier
                    .background(Color.Black)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MovieDetailsTitlePart(
                            movieTitle = movie.title,
                            genre = movie.genres,
                            votes = movie.voteCount.toString(),
                            rating = movie.voteAverage
                        )
                        Column(modifier = Modifier.padding(9.dp)) {
                            movie.originalLanguage?.let {
                                val icon =
                                    IconString(
                                        R.drawable.ic_baseline_language_24,
                                        getMovieLanguage(it)
                                    )
                                MovieDetailsTitleSecondPart(iconString = icon)
                            }
                            movie.runtime?.let {
                                val icon2 =
                                    IconString(
                                        R.drawable.ic_baseline_access_time_24,
                                        getMovieRuntime(it)
                                    )
                                MovieDetailsTitleSecondPart(iconString = icon2)
                            }
                            movie.releaseDate?.let {
                                val icon3 = IconString(R.drawable.ic_baseline_event_24, it)
                                MovieDetailsTitleSecondPart(iconString = icon3)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    movie.overview?.let { MovieDetailsComposable(details = it) }
                    Spacer(modifier = Modifier.padding(4.dp))
                    viewModel.videos.value?.let {
                        Text(
                            text = "VIDEOS",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        VideoRowListComposable(videos = it, onClick = { link ->
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("${Constants.BASE_YT_WATCH_URL}${link}")
                            )
                            context.startActivity(intent)
                        })
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    viewModel.casts.value?.let {
                        Text(
                            text = "CAST",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        CastRowListComposable(casts = it, onClick = { id ->
                            navController.navigate("${BottomNavItem.castDeails.screen_route}/${id}")
                        })
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieTypeScreen(
    viewModel: MoviesCategoryListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController,
    type: String
) {
    viewModel.loadData(type)
    val isDisplayed = viewModel.isLoading

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        ShowContent(isConnected = viewModel.isConnected) {
            CircularIndeterminateProgressBar(isDisplayed = isDisplayed.value)
            AppBar(title = type, navController = navController)
            MovieGridListComposable(movies = viewModel.movieList, onClick = {
                navController.navigate("${BottomNavItem.details.screen_route}/$it")
            })
        }
    }
}

@Composable
fun CastDetailsComposable(
    viewModel: PersonViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController,
    type: Int
) {
    viewModel.loadData(type.toLong())
    val isDisplayed = viewModel.isLoading
    val person = viewModel.person.value
    val images = viewModel.images.value
    val credits = viewModel.credits.value
    Column(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowContent(isConnected = viewModel.isConnected) {
            CircularIndeterminateProgressBar(isDisplayed = isDisplayed.value)
            AppBar(title = person.name, navController = navController)
            PersonDetailsHeaderComposable(
                person.profilePath,
                person.name,
                person.knownForDepartment
            )
            LazyColumn(modifier = Modifier.padding(3.dp)) {
                item {
                    person.biography?.let { BiographyComposable("Biography", it) }
                    Spacer(modifier = Modifier.padding(5.dp))
                    images?.let {
                        Text(
                            text = "IMAGES",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        PicsRowListComposable(it)
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    credits?.let {
                        Text(
                            text = "CREDITS",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        CreditsRowListComposable(it) { id ->
                            navController.navigate("${BottomNavItem.details.screen_route}/${id.toLong()}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TvDetailsComposable(
    viewModel: TvDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController,
    id: Long?
) {
    id?.let { viewModel.loadData(it) }
    val isDisplayed = viewModel.isLoading
    val movie = viewModel.tvDetails.value
    val context = LocalContext.current
    ShowContent(isConnected = viewModel.isConnected) {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            CircularIndeterminateProgressBar(isDisplayed = isDisplayed.value)
            AppBar(title = movie.originalName, navController = navController)
            movie.backdropPath?.let { MovieImageBigComposable(imageUrl = it) }
            Spacer(modifier = Modifier.padding(4.dp))
            LazyColumn(
                modifier = Modifier
                    .background(Color.Black)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MovieDetailsTitlePart(
                            movieTitle = movie.originalName,
                            genre = movie.genres,
                            votes = movie.voteCount.toString(),
                            rating = movie.voteAverage
                        )
                        Column(modifier = Modifier.padding(9.dp)) {
                            movie.firstAirDate.let {
                                val icon =
                                    IconString(
                                        R.drawable.ic_baseline_event_24,
                                        getMovieLanguage(it)
                                    )
                                MovieDetailsTitleSecondPart(iconString = icon)
                            }
                            movie.numberOfEpisodes.let { episodes ->
                                val icon2 =
                                    IconString(
                                        R.drawable.ic_baseline_live_tv_24,
                                        "Episodes: $episodes"
                                    )
                                MovieDetailsTitleSecondPart(iconString = icon2)
                            }
                            movie.numberOfSeasons.let { seasons ->
                                val icon3 =
                                    IconString(
                                        R.drawable.ic_baseline_live_tv_24,
                                        "Seasons: $seasons"
                                    )
                                MovieDetailsTitleSecondPart(iconString = icon3)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    movie.overview.let { MovieDetailsComposable(details = it) }
                    Spacer(modifier = Modifier.padding(4.dp))
                    viewModel.videos.value?.let {
                        if (it.isNotEmpty()) {
                            Text(
                                text = "VIDEOS",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            VideoRowListComposable(videos = it, onClick = { link ->
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("${Constants.BASE_YT_WATCH_URL}${link}")
                                )
                                context.startActivity(intent)
                            })
                        }
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    viewModel.casts.value?.let {
                        if (it.isNotEmpty()) {
                            Text(
                                text = "CAST",
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            CastRowListComposable(casts = it, onClick = { id ->
                                navController.navigate("${BottomNavItem.castDeails.screen_route}/${id}")
                            })
                        }
                    }
                }
            }
        }
    }
}
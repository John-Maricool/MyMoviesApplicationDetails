package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.ui.MainViewModel
import com.maricoolsapps.sportsapplication.utils.BottomNavItem

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun MoviesScreen(viewModel: MainViewModel, navController: NavHostController) {
    viewModel.getAllMovies()
    MovieGridListComposable(movies = viewModel.allMovieList, onClick = {
        navController.navigate(BottomNavItem.details.screen_route)
    })
}

@ExperimentalFoundationApi
@Composable
fun TvShowsScreen(viewModel: MainViewModel, navController: NavHostController) {
    viewModel.getTvShows()
    MovieGridListComposable(movies = viewModel.tvShowList, onClick = {
        navController.navigate(BottomNavItem.details.screen_route)
    })
}


@Composable
fun DetailsScreen(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}
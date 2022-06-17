package com.maricoolsapps.sportsapplication.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maricoolsapps.sportsapplication.ui.MainViewModel
import com.maricoolsapps.sportsapplication.ui.composables.DetailsScreen
import com.maricoolsapps.sportsapplication.ui.composables.HomeScreen
import com.maricoolsapps.sportsapplication.ui.composables.MoviesScreen
import com.maricoolsapps.sportsapplication.ui.composables.TvShowsScreen

@ExperimentalFoundationApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.home.screen_route) {
        composable(BottomNavItem.home.screen_route) {
            HomeScreen(viewModel)
        }
        composable(BottomNavItem.movies.screen_route) {
            MoviesScreen(viewModel, navController)
        }
        composable(BottomNavItem.tvShows.screen_route) {
            TvShowsScreen(viewModel, navController)
        }
        composable(BottomNavItem.details.screen_route) {
            DetailsScreen(viewModel)
        }
    }
}
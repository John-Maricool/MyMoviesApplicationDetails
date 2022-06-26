package com.maricoolsapps.sportsapplication.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maricoolsapps.sportsapplication.ui.MainViewModel
import com.maricoolsapps.sportsapplication.ui.composables.*

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.home.screen_route,
        modifier = Modifier.background(
            Color.Black
        )
    ) {
        composable(BottomNavItem.home.screen_route) {
            HomeScreen(viewModel, navController)
        }
        composable(BottomNavItem.movies.screen_route) {
            MoviesScreen(viewModel, navController)
        }
        composable(BottomNavItem.tvShows.screen_route) {
            TvShowsScreen(viewModel, navController)
        }
        composable("${BottomNavItem.details.screen_route}/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
            }
        )
        ) { navBackStackEntry ->
            DetailsScreen(
                viewModel,
                navController,
                navBackStackEntry.arguments?.getLong("id") as Long
            )
        }
        composable(
            "${BottomNavItem.moviesType.screen_route}/{type}", arguments = listOf(
                navArgument("type") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            MovieTypeScreen(
                viewModel,
                navController,
                navBackStackEntry.arguments?.getInt("type") as Int
            )
        }
        composable(
            "${BottomNavItem.castDeails.screen_route}/{type}", arguments = listOf(
                navArgument("type") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            CastDetailsComposable(
                viewModel,
                navController,
                navBackStackEntry.arguments?.getInt("type") as Int
            )
        }
    }
}
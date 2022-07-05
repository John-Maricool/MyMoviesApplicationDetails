package com.maricoolsapps.sportsapplication.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maricoolsapps.sportsapplication.ui.composables.*
import com.maricoolsapps.sportsapplication.ui.view_models.*

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.home.screen_route,
        modifier = Modifier.background(
            Color.Black
        )
    ) {
        composable(BottomNavItem.home.screen_route) {
            val viewModel = hiltViewModel<MainViewModel>()
            HomeScreen(viewModel = viewModel, navController)
        }
        composable(BottomNavItem.movies.screen_route) {
            val viewModel = hiltViewModel<MoviesListViewModel>()

            MoviesScreen(
                viewModel = viewModel,
                navController
            )
        }
        composable(BottomNavItem.tvShows.screen_route) {
            val viewModel = hiltViewModel<TvShowsListViewModel>()
            TvShowsScreen(
                viewModel = viewModel,
                navController
            )
        }
        composable("${BottomNavItem.details.screen_route}/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
            }
        )
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<MovieDetailsViewModel>()

            DetailsScreen(
                viewModel = viewModel,
                navController,
                navBackStackEntry.arguments?.getLong("id") as Long
            )
        }
        composable(
            "${BottomNavItem.moviesType.screen_route}/{type}", arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<MoviesCategoryListViewModel>()
            MovieTypeScreen(
                viewModel = viewModel,
                navController,
                navBackStackEntry.arguments?.getString("type") as String
            )
        }
        composable(
            "${BottomNavItem.castDeails.screen_route}/{type}", arguments = listOf(
                navArgument("type") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<PersonViewModel>()
            CastDetailsComposable(
                viewModel = viewModel,
                navController,
                navBackStackEntry.arguments?.getInt("type") as Int
            )
        }

        composable(
            "${BottomNavItem.tvDetails.screen_route}/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) { navBackStackEntry ->
            val viewModel = hiltViewModel<TvDetailsViewModel>()
            TvDetailsComposable(
                viewModel = viewModel,
                navController,
                navBackStackEntry.arguments?.getLong("id")
            )
        }
    }
}
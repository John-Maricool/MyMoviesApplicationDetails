package com.maricoolsapps.sportsapplication.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maricoolsapps.sportsapplication.ui.composables.BottomNavigation
import com.maricoolsapps.sportsapplication.utils.BottomNavItem
import com.maricoolsapps.sportsapplication.utils.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenView(viewModel)
        }
    }

    @Composable
    fun MainScreenView(viewModel: MainViewModel) {
        val navController = rememberNavController()
        Scaffold(bottomBar = {
            if ((currentRoute(navController) == BottomNavItem.home.screen_route) ||
                (currentRoute(navController) == BottomNavItem.tvShows.screen_route) ||
                (currentRoute(navController) == BottomNavItem.movies.screen_route)
            )
                BottomNavigation(navController = navController)
        }
        ) {
            NavigationGraph(navController = navController, viewModel = viewModel)
        }
    }

    @Composable
    fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }
}



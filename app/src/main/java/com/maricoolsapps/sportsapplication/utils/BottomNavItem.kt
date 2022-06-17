package com.maricoolsapps.sportsapplication.utils

import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.utils.Constants.DETAILS_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.HOME_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.MOVIES_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.TVSHOWS_ROUTE

sealed class BottomNavItem(
    var title: String, var icon: Int?, var screen_route: String
) {
    object home : BottomNavItem("Home", R.drawable.ic_home, HOME_ROUTE)
    object details : BottomNavItem("Details", R.drawable.ic_home, DETAILS_ROUTE)
    object tvShows : BottomNavItem("Movies", R.drawable.ic_tv, TVSHOWS_ROUTE)
    object movies : BottomNavItem("TV shows", R.drawable.ic_movie, MOVIES_ROUTE)
}
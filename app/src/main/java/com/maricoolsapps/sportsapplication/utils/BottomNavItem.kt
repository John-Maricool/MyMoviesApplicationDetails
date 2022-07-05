package com.maricoolsapps.sportsapplication.utils

import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.utils.Constants.CAST_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.DETAILS_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.HOME_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.MOVIES_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.MOVIE_CATEGORY_GRID_LIST
import com.maricoolsapps.sportsapplication.utils.Constants.TVSHOWS_ROUTE
import com.maricoolsapps.sportsapplication.utils.Constants.TV_LIST_DETAILS

sealed class BottomNavItem(
    var title: String, var icon: Int?, var screen_route: String
) {
    object home : BottomNavItem("Home", R.drawable.ic_home, HOME_ROUTE)
    object tvShows : BottomNavItem("TV shows", R.drawable.ic_tv, TVSHOWS_ROUTE)
    object movies : BottomNavItem("Movies", R.drawable.ic_movie, MOVIES_ROUTE)
    object details : BottomNavItem("Details", null, DETAILS_ROUTE)
    object castDeails : BottomNavItem("Cast Details", null, CAST_ROUTE)
    object moviesType :
        BottomNavItem("Movie Category", null, MOVIE_CATEGORY_GRID_LIST)

    object tvDetails :
        BottomNavItem("TV Category Details", null, TV_LIST_DETAILS)

}
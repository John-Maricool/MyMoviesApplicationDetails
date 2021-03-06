package com.maricoolsapps.sportsapplication.utils

import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.MoviesListItem
import java.util.*

fun getMovieLanguage(languageCode: String?): String {
    return Locale(languageCode).getDisplayLanguage(Locale("en"))
}

fun getMovieRuntime(runtimeInMinutes: Int?): String {
    val hoursText: String? = runtimeInMinutes?.div(60f)?.let { appendZeroBeforeNumber(it.toInt()) }
    val minutesText: String? =
        runtimeInMinutes?.rem(60f)?.let { appendZeroBeforeNumber(it.toInt()) }
    return "$hoursText:$minutesText / $runtimeInMinutes min"
}

fun appendZeroBeforeNumber(num: Int): String {
    return if (num < 10) "0$num" else num.toString()
}

fun getRuntimeDateFormat() = ("yyyy-MM-dd")

fun mapToDataModel(data: MoviesListItem): MovieListItemModel {
    return MovieListItemModel(data.image, data.id, data.title)
}

fun mapAllToDataModel(data: List<MoviesListItem>): List<MovieListItemModel> {
    return data.map {
        mapToDataModel(it)
    }
}
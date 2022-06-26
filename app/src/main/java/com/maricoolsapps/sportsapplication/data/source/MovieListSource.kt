package com.maricoolsapps.sportsapplication.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.MoviesListItem
import com.maricoolsapps.sportsapplication.data.models.TvListItem
import com.maricoolsapps.sportsapplication.utils.Constants.ALL_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.IN_THEATRE_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.POPULAR_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.UPCOMING_MOVIES

class MovieListSource(
    private val type: Int,
    private val api: Api
) : PagingSource<Int, MovieListItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> {
        return try {
            val nextPage = params.key ?: 1
            var dataList = listOf<MovieListItemModel>()
            when (type) {
                POPULAR_MOVIES -> {
                    val moviesList = api.getPopularMovies(
                        page = nextPage
                    )
                    dataList = mapAllToDataModel(moviesList.results)
                }
                IN_THEATRE_MOVIES -> {
                    val moviesList = api.getInTheatreMovies(
                        page = nextPage
                    )
                    dataList = mapAllToDataModel(moviesList.results)
                }
                UPCOMING_MOVIES -> {
                    val moviesList = api.getUpcomingMovies(
                        page = nextPage
                    )
                    dataList = mapAllToDataModel(moviesList.results)
                }
                ALL_MOVIES -> {
                    val moviesList = api.getAllMovies(
                        page = nextPage
                    )
                    dataList = mapAllToDataModel(moviesList.results)
                }
            }
            LoadResult.Page(
                data = dataList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (dataList.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}


fun mapToDataModel(data: MoviesListItem): MovieListItemModel {
    return MovieListItemModel(data.image, data.id, data.title)
}


fun mapToDataModel(data: TvListItem): MovieListItemModel {
    return MovieListItemModel(data.image, data.id, data.title)
}

fun mapAllToDataModel(data: List<MoviesListItem>): List<MovieListItemModel> {
    return data.map {
        mapToDataModel(it)
    }
}











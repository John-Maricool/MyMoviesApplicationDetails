package com.maricoolsapps.sportsapplication.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.TvListItem

class TvListSource(
    private val api: Api
) : PagingSource<Int, MovieListItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> {
        return try {
            val nextPage = params.key ?: 1
            val tvList = api.getTvShows(
                page = nextPage
            )
            val results = mapAllTvToDataModel(tvList.results)
            LoadResult.Page(
                data = results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvList.results.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}


fun mapAllTvToDataModel(data: List<TvListItem>): List<MovieListItemModel> {
    return data.map {
        mapToDataModel(it)
    }
}
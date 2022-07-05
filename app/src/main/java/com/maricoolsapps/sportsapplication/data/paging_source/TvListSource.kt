package com.maricoolsapps.sportsapplication.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.TvListItem
import com.maricoolsapps.sportsapplication.utils.mapAllToDataModel

class AllMoviesAndTvSource(
    private val api: Api,
    private val type: String
) : PagingSource<Int, MovieListItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> {
        return try {
            val nextPage = params.key ?: 1
            val list = api.getAllMoviesAndTv(
                page = nextPage,
                type = type
            )
            val results = mapAllToDataModel(list.results)
            LoadResult.Page(
                data = results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (list.results.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
package com.maricoolsapps.sportsapplication.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maricoolsapps.sportsapplication.api.Api
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.MoviesListItem
import com.maricoolsapps.sportsapplication.data.models.TvListItem
import com.maricoolsapps.sportsapplication.utils.mapAllToDataModel

class MovieListSource(
    private val api: Api,
    private val type: String
) : PagingSource<Int, MovieListItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> {
        return try {
            val nextPage = params.key ?: 1
            val dataList: List<MovieListItemModel>
            val moviesList = api.getMovieCategory(
                type = type,
                page = nextPage
            )
            dataList = mapAllToDataModel(moviesList.results)
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











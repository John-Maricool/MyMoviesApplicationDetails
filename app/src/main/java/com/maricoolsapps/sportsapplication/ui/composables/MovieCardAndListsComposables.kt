package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.utils.CircularIndeterminateProgressBar
import com.maricoolsapps.sportsapplication.utils.LoadingMovieListShimmer
import com.maricoolsapps.sportsapplication.utils.MovieCard
import kotlinx.coroutines.flow.Flow

val DEFAULT_NEWS_IMAGE = R.drawable.ic_image_default


@ExperimentalFoundationApi
@Composable
fun MovieGridListComposable(
    movies: Flow<PagingData<MovieListItemModel>>,
    onClick: (id: Long) -> Unit,
) {
    val movieItem: LazyPagingItems<MovieListItemModel> = movies.collectAsLazyPagingItems()
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.background(Color.Black), userScrollEnabled = true) {
        items(movieItem.itemCount) { index ->
            val item = movieItem[index]
            item?.let {
                MovieCard(url = it.image, it.title) {
                    onClick(it.id)
                }
            }
        }
        movieItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    items(100) {
                        LoadingMovieListShimmer()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        CircularIndeterminateProgressBar(true)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieRowListComposable(
    movies: List<MovieListItemModel>,
    onClick: (id: Long) -> Unit,
) {
    LazyRow {
        items(movies) { item ->
            item.let {
                MovieCard(url = it.image, text = it.title) {
                    onClick(it.id)
                }
            }
        }
    }
}

@Composable
fun HomeHeaderComposable(
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "show all",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .padding(3.dp)
                .clickable(
                    enabled = true,
                    onClick = onClick
                )
                .align(CenterVertically)
        )
    }
}


@Composable
fun MovieRowListWithHeader(
    movies: List<MovieListItemModel>,
    onClick: (id: Long) -> Unit,
    name: String,
    onClickShowAll: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.Black)
    ) {
        HomeHeaderComposable(name = name, onClick = onClickShowAll)
        MovieRowListComposable(movies = movies, onClick = onClick)
    }
}


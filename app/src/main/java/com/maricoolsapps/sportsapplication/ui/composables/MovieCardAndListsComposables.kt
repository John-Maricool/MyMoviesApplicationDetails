package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maricoolsapps.sportsapplication.R
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.MoviesListItem
import com.maricoolsapps.sportsapplication.utils.Constants.BASE_POSTER_URL
import com.maricoolsapps.sportsapplication.utils.loadPicture
import kotlinx.coroutines.flow.Flow


val DEFAULT_NEWS_IMAGE = R.drawable.ic_image_default

@Composable
private fun MovieCard(
    item: MovieListItemModel,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick)
            .background(color = Color.Black)
            .width(40.dp),
        elevation = 8.dp
    ) {
        Column {
            val image =
                item.image?.let {
                    loadPicture(
                        url = "${BASE_POSTER_URL}${it}",
                        defaultImg = DEFAULT_NEWS_IMAGE
                    ).value
                }
            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image",
                )
            }
            item.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 13.sp,
                        fontStyle = FontStyle(3)
                    ),
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MovieGridListComposable(
    movies: Flow<PagingData<MovieListItemModel>>,
    onClick: (id: Long) -> Unit,
) {
    val movieItem: LazyPagingItems<MovieListItemModel> = movies.collectAsLazyPagingItems()

    LazyVerticalGrid(cells = GridCells.Fixed(3), modifier = Modifier.background(Color.Black)) {
        items(movieItem.itemCount) { index ->
            val item = movieItem[index]
            item?.let {
                MovieCard(item = it) {
                    onClick(it.id)
                }
            }
        }
    }

    /* movieItem.apply {
         when {
             loadState.refresh is LoadState.Loading -> {
                 items(8) {
                   //  LoadingNewsListShimmer()
                 }
             }
             loadState.append is LoadState.Loading -> {
                 item {
                     //CircularIndeterminateProgressBar(isDisplayed = true)
                 }
             }
             loadState.refresh is LoadState.Error -> {
                 val e = movieItem.loadState.refresh as LoadState.Error
                 item {
                     //ShowSnackBar(text = e.error.message.toString())
                 }
             }
             loadState.append is LoadState.Error -> {
                 val e = movieItem.loadState.append as LoadState.Error
                 item {
                     //ShowSnackBar(text = e.error.message.toString())
                 }
             }
         }
     }*/
}


@Composable
fun MovieRowListComposable(
    movies: Flow<PagingData<MovieListItemModel>>,
    onClick: (id: Long) -> Unit
) {
    val movieItem: LazyPagingItems<MovieListItemModel> = movies.collectAsLazyPagingItems()

    LazyRow {
        items(movieItem.itemCount) { index ->
            val item = movieItem[index]
            item?.let {
                MovieCard(item = it) {
                    onClick(it.id)
                }
            }
        }
    }

    /* movieItem.apply {
         when {
             loadState.refresh is LoadState.Loading -> {
                 items(8) {
                   //  LoadingNewsListShimmer()
                 }
             }
             loadState.append is LoadState.Loading -> {
                 item {
                     //CircularIndeterminateProgressBar(isDisplayed = true)
                 }
             }
             loadState.refresh is LoadState.Error -> {
                 val e = movieItem.loadState.refresh as LoadState.Error
                 item {
                     //ShowSnackBar(text = e.error.message.toString())
                 }
             }
             loadState.append is LoadState.Error -> {
                 val e = movieItem.loadState.append as LoadState.Error
                 item {
                     //ShowSnackBar(text = e.error.message.toString())
                 }
             }
         }
     }*/
}

@Composable
fun HomeHeaderComposable(
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontStyle = FontStyle(3)
            ),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "show all",
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                fontStyle = FontStyle(2)
            ),
            modifier = Modifier
                .padding(3.dp)
                .clickable(
                    enabled = true,
                    onClick = onClick
                )
        )
    }
}


package com.maricoolsapps.sportsapplication.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerNewsCardItem(
    brush: Brush
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
               8.dp
            )
            .width(110.dp)
            .background(Color.Black),
    ) {
        Column {

            Spacer(
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .background(brush),
            )
            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )
            Spacer(
                modifier = Modifier
                    .background(brush)
                    .height(14.dp)
                    .width(110.dp),
            )
            Spacer(
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}
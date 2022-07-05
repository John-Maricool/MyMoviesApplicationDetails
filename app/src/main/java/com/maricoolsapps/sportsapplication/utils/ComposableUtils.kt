package com.maricoolsapps.sportsapplication.utils

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.maricoolsapps.sportsapplication.R

@Composable
fun AppBar(title: String, navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.h5,
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Rounded.ArrowBack, "", tint = Color.White)
            }
        },
        backgroundColor = Color.Black
    )
}

val Roboto = FontFamily(
    Font(R.font.roboto_condensed_bold, FontWeight.W500),
    Font(R.font.roboto_condensed_regular, FontWeight.W200)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 34.sp,
        color = Color.LightGray
    ),

    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.LightGray
    ),

    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
        color = Color.LightGray
    ),

    body2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp,
        color = Color.LightGray
    ),

    h4 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light,
        fontSize = 15.sp,
        color = Color.LightGray
    )
)
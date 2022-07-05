package com.maricoolsapps.sportsapplication.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.maricoolsapps.sportsapplication.utils.ShowSnackBar
import com.maricoolsapps.sportsapplication.utils.Typography

@Composable
fun ShowContent(isConnected: Boolean, content: @Composable (() -> Unit)) {
    if (isConnected) {
        MaterialTheme(typography = Typography) {
            content()
        }
    } else {
        ShowSnackBar(text = "No InternetConnection")
    }
}
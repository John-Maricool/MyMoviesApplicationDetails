package com.maricoolsapps.sportsapplication.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget

@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImg: Int
): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImg)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                placeholder.toString()
            }
        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                placeholder.toString()
            }
        })
    return bitmapState
}


@Composable
fun loadRoundedPicture(
    url: String,
    @DrawableRes defaultImg: Int
): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImg)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                placeholder.toString()
            }
        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .circleCrop()
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                placeholder.toString()
            }
        })
    return bitmapState
}
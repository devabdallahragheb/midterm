package com.example.testapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Represents an image item with a title and image resource.
 * Uses type-safe resource annotations for compile-time safety.
 */
data class ImageItem(
    @StringRes val titleResId: Int,
    @DrawableRes val imageResId: Int
)

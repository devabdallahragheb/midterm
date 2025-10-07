package com.example.testapp.data

import com.example.testapp.R
import com.example.testapp.model.ImageItem

/**
 * Static data source containing the list of images and their titles.
 * This should not be accessed directly by UI or ViewModel - use Repository instead.
 */
object ImageDataSource {
    val images = listOf(
        ImageItem(
            titleResId = R.string.title_miu_campus,
            imageResId = R.drawable.miu_campus
        ),
        ImageItem(
            titleResId = R.string.title_faculty_student,
            imageResId = R.drawable.faculty_student
        ),
        ImageItem(
            titleResId = R.string.title_sustainable_living,
            imageResId = R.drawable.sustainable_living_center
        ),
        ImageItem(
            titleResId = R.string.title_rainbow,
            imageResId = R.drawable.rainbow
        )
    )
}

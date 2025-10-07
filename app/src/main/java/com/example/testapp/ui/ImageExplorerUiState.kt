package com.example.testapp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Immutable UI state for the Image Explorer screen.
 * Contains everything the UI needs to render - no business logic or mutable fields.
 */
data class ImageExplorerUiState(
    @StringRes val currentTitleResId: Int,
    @DrawableRes val currentImageResId: Int
)

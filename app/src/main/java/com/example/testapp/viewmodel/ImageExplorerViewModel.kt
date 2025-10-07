package com.example.testapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testapp.repository.ImageRepository
import com.example.testapp.ui.ImageExplorerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Image Explorer screen.
 * Exposes UI state as StateFlow and handles navigation logic.
 * Does not expose mutable state - uses private mutable flow and public read-only flow.
 */
class ImageExplorerViewModel(
    private val repository: ImageRepository
) : ViewModel() {
    
    // Private mutable state
    private val _uiState = MutableStateFlow(initializeUiState())
    
    // Public read-only state
    val uiState: StateFlow<ImageExplorerUiState> = _uiState.asStateFlow()
    
    // Current index (internal state)
    private var currentIndex = 0
    
    /**
     * Initialize UI state from repository.
     */
    private fun initializeUiState(): ImageExplorerUiState {
        val firstItem = repository.getItemAt(0)
        return ImageExplorerUiState(
            currentTitleResId = firstItem.titleResId,
            currentImageResId = firstItem.imageResId
        )
    }
    
    /**
     * Advances to the next item with wrap-around.
     */
    fun goToNext() {
        currentIndex = repository.getNextIndex(currentIndex)
        val nextItem = repository.getItemAt(currentIndex)
        _uiState.value = ImageExplorerUiState(
            currentTitleResId = nextItem.titleResId,
            currentImageResId = nextItem.imageResId
        )
    }
}

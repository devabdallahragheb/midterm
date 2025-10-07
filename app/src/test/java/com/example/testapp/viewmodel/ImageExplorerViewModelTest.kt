package com.example.testapp.viewmodel

import com.example.testapp.model.ImageItem
import com.example.testapp.repository.ImageRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit test for ImageExplorerViewModel.
 * Verifies that calling goToNext once after initialization updates UIState
 * from the first item to the second item.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ImageExplorerViewModelTest {
    
    private lateinit var viewModel: ImageExplorerViewModel
    private lateinit var repository: ImageRepository
    
    // Test data
    private val testItems = listOf(
        ImageItem(titleResId = 1, imageResId = 101),
        ImageItem(titleResId = 2, imageResId = 102),
        ImageItem(titleResId = 3, imageResId = 103),
        ImageItem(titleResId = 4, imageResId = 104)
    )
    
    @Before
    fun setup() {
        // Create a fake repository for testing
        repository = object : ImageRepository {
            override fun getAll(): List<ImageItem> = testItems
            
            override fun getNextIndex(currentIndex: Int): Int {
                return (currentIndex + 1) % testItems.size
            }
            
            override fun getItemAt(index: Int): ImageItem {
                return testItems[index]
            }
        }
        
        viewModel = ImageExplorerViewModel(repository)
    }
    
    @Test
    fun `goToNext updates UIState from first item to second item`() = runTest {
        // Initial state should be the first item
        val initialState = viewModel.uiState.value
        assertEquals(testItems[0].titleResId, initialState.currentTitleResId)
        assertEquals(testItems[0].imageResId, initialState.currentImageResId)
        
        // Call goToNext once
        viewModel.goToNext()
        
        // State should now be the second item
        val updatedState = viewModel.uiState.value
        assertEquals(testItems[1].titleResId, updatedState.currentTitleResId)
        assertEquals(testItems[1].imageResId, updatedState.currentImageResId)
    }
    
    @Test
    fun `goToNext wraps around after last item`() = runTest {
        // Navigate to the last item
        repeat(3) { viewModel.goToNext() }
        
        val stateAtLast = viewModel.uiState.value
        assertEquals(testItems[3].titleResId, stateAtLast.currentTitleResId)
        
        // Call goToNext once more - should wrap to first item
        viewModel.goToNext()
        
        val wrappedState = viewModel.uiState.value
        assertEquals(testItems[0].titleResId, wrappedState.currentTitleResId)
        assertEquals(testItems[0].imageResId, wrappedState.currentImageResId)
    }
}

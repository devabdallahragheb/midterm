package com.example.testapp.repository

import com.example.testapp.model.ImageItem

/**
 * Repository interface that provides access to image items.
 * This abstraction allows for easy testing and future data source changes.
 */
interface ImageRepository {
    /**
     * Returns all available image items.
     */
    fun getAll(): List<ImageItem>
    
    /**
     * Returns the next item given the current index.
     * Wraps around to the first item after the last.
     */
    fun getNextIndex(currentIndex: Int): Int
    
    /**
     * Returns the item at the specified index.
     */
    fun getItemAt(index: Int): ImageItem
}

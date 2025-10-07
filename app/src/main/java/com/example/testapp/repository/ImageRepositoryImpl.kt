package com.example.testapp.repository

import com.example.testapp.data.ImageDataSource
import com.example.testapp.model.ImageItem

/**
 * Implementation of ImageRepository that reads from the static data source.
 */
class ImageRepositoryImpl : ImageRepository {
    private val items = ImageDataSource.images
    
    override fun getAll(): List<ImageItem> = items
    
    override fun getNextIndex(currentIndex: Int): Int {
        return (currentIndex + 1) % items.size
    }
    
    override fun getItemAt(index: Int): ImageItem {
        return items[index]
    }
}

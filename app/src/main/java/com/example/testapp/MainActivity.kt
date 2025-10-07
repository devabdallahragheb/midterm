package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.testapp.repository.ImageRepositoryImpl
import com.example.testapp.ui.ImageExplorerScreen
import com.example.testapp.ui.theme.TestappTheme
import com.example.testapp.viewmodel.ImageExplorerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Create repository and ViewModel
        val repository = ImageRepositoryImpl()
        val viewModel = ImageExplorerViewModel(repository)
        
        setContent {
            TestappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImageExplorerScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
package com.example.testapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testapp.R
import com.example.testapp.viewmodel.ImageExplorerViewModel

/**
 * Image Explorer screen that displays an image, title, and next button.
 * Renders strictly from UIState collected from ViewModel.
 */
@Composable
fun ImageExplorerScreen(
    viewModel: ImageExplorerViewModel,
    modifier: Modifier = Modifier
) {
    // Collect state from ViewModel
    val uiState by viewModel.uiState.collectAsState()
    
    // Render from UIState
    ImageExplorerContent(
        uiState = uiState,
        onNextClick = { viewModel.goToNext() },
        modifier = modifier
    )
}

/**
 * Stateless composable that renders the UI from UIState.
 */
@Composable
fun ImageExplorerContent(
    uiState: ImageExplorerUiState,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image with cropped fit, fills width, uses title for content description
        Image(
            painter = painterResource(id = uiState.currentImageResId),
            contentDescription = stringResource(id = uiState.currentTitleResId),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        
        // Title text
        Text(
            text = stringResource(id = uiState.currentTitleResId),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        
        // Next button
        Button(
            onClick = onNextClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.button_next))
        }
    }
}

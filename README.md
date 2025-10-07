# Image Explorer App

A small Android image explorer application built with Jetpack Compose that displays images with titles and allows navigation through them with wrap-around functionality.

## Screenshots

### Running App - MIU Campus
![App Screenshot - MIU Campus](screenshots/Screenshot%202025-10-07%20at%209.53.09%20AM.png)
*Image Explorer showing MIU Campus with title and Next button*

### Running App - Faculty and Students
![App Screenshot - Faculty](screenshots/Screenshot%202025-10-07%20at%209.54.24%20AM.png)
*Image Explorer showing Faculty and Students with title and Next button*

## Features

### Functional Requirements
1. **Launch Display**: On launch, the screen displays the first image and its title from a data source
2. **Navigation**: Tapping "Next" advances to the next item; after the last item, it wraps to the first
3. **Image Display**: Images fill the width and maintain a reasonable aspect ratio with cropped fit
4. **Accessibility**: Image content descriptions use the same title shown in text

### UI Components
- **Image**: Displays the current image with cropped fit
- **Title Text**: Shows the title below the image
- **Next Button**: Advances to the next image/title pair

## Architecture

This app follows clean architecture principles with clear separation of concerns:

### 1. UIState (`ImageExplorerUiState.kt`)
- Immutable data class representing everything the UI needs
- Contains: `currentTitleResId` and `currentImageResId`
- No business logic or mutable fields
- Uses type-safe `@StringRes` and `@DrawableRes` annotations

### 2. ViewModel (`ImageExplorerViewModel.kt`)
- Exposes state as `StateFlow<ImageExplorerUiState>`
- Holds current index internally
- Provides `goToNext()` method to advance to next item
- Initializes UIState from repository
- Uses private mutable flow and public read-only flow (no mutable state exposed)

### 3. Repository Pattern
- **Interface** (`ImageRepository.kt`): Defines contract for data access
  - `getAll()`: Returns all items
  - `getNextIndex(currentIndex)`: Calculates next index with wrap-around
  - `getItemAt(index)`: Returns item at specific index
- **Implementation** (`ImageRepositoryImpl.kt`): Reads from static data source
- UI and ViewModel access data only through repository (not directly)

### 4. Static Data Source (`ImageDataSource.kt`)
- Defines static list of 4 image items
- Each item contains:
  - String resource ID for title
  - Drawable resource ID for image
- Uses type-safe annotations (`@StringRes`, `@DrawableRes`)

### 5. Model (`ImageItem.kt`)
- Data class with type-safe resource annotations
- Fields: `titleResId` and `imageResId`

### 6. UI (Compose) (`ImageExplorerScreen.kt`)
- Uses Column containing Image, Text, and Button
- Collects state from ViewModel
- Renders strictly from UIState
- Stateless content composable for easy testing

## Project Structure

```
app/src/main/java/com/example/testapp/
├── MainActivity.kt                          # Entry point
├── model/
│   └── ImageItem.kt                         # Data model
├── data/
│   └── ImageDataSource.kt                   # Static data source
├── repository/
│   ├── ImageRepository.kt                   # Repository interface
│   └── ImageRepositoryImpl.kt               # Repository implementation
├── viewmodel/
│   └── ImageExplorerViewModel.kt            # ViewModel with StateFlow
└── ui/
    ├── ImageExplorerUiState.kt              # Immutable UI state
    └── ImageExplorerScreen.kt               # Compose UI

app/src/test/java/com/example/testapp/
└── viewmodel/
    └── ImageExplorerViewModelTest.kt        # Unit tests
```

## Images Used

The app displays 4 images from the MIU campus:
1. **MIU Campus** - Main campus view
2. **Faculty and Students** - Faculty-student interaction
3. **Sustainable Living Center** - Campus sustainability center
4. **Rainbow at MIU** - Beautiful rainbow over campus

All images are located in `res/drawable/`.

## Testing

### Unit Test (`ImageExplorerViewModelTest.kt`)
- **Primary Test**: Verifies that calling `goToNext()` once after initialization updates UIState from the first item to the second item
- **Additional Test**: Verifies wrap-around functionality after the last item
- Uses fake repository for isolated testing
- Uses Kotlin coroutines test library

### Running Tests
```bash
./gradlew test
```

Or run from Android Studio:
- Right-click on `ImageExplorerViewModelTest.kt`
- Select "Run 'ImageExplorerViewModelTest'"

## UX Details

- **Center Alignment**: All components are centered horizontally
- **Reasonable Padding**: 16dp padding around components
- **Content Scale**: Uses `ContentScale.Crop` for photos
- **Material Design**: Uses Material 3 components and typography

## Dependencies

- **Jetpack Compose**: Modern declarative UI toolkit
- **ViewModel Compose**: `androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0`
- **Lifecycle Runtime Compose**: `androidx.lifecycle:lifecycle-runtime-compose:2.7.0`
- **Coroutines Test**: `kotlinx-coroutines-test:1.7.3` (for testing)

## Building and Running

1. **Sync Gradle**: Open project in Android Studio and sync Gradle
2. **Build**: `./gradlew build`
3. **Run**: Click "Run" in Android Studio or use `./gradlew installDebug`

## Requirements Met

✅ UIState: Immutable data class with no business logic  
✅ ViewModel: StateFlow exposure, private mutable/public read-only  
✅ Repository Pattern: Interface + Implementation, no direct data access  
✅ Static Data Source: 4+ items with type-safe annotations  
✅ UI (Compose): Column with Image, Text, Button rendering from UIState  
✅ UX: Center alignment, padding, cropped fit  
✅ Testing: Unit test verifying first-to-second item transition  
✅ Accessibility: Content descriptions match titles  

## Author

Built for MIU Android Development Course - Midterm Project

## License

Educational project for MIU coursework.


this screen shoot for app
<img width="1694" height="1078" alt="Screenshot 2025-10-07 at 9 50 56 AM" src="https://github.com/user-attachments/assets/881db4e4-72a5-4402-9e36-a63a6b27a53c" />


this screen shoot for unit test<img width="1601" height="1003" alt="Screenshot 2025-10-07 at 10 04 33 AM" src="https://github.com/user-attachments/assets/f5619dea-b3be-4ef0-b57e-134e653dce58" />






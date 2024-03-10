# Joel's Bookstore

Joel's Bookstore is an Android application inspired by the character Joel Barish. This app allows users to browse, discover, and save their favorite books. It provides detailed information on various books, including authors, rankings, and personal booklists.

![Joel's Bookstore](https://github.com/mergencdev/joelsbookstore/blob/main/banner/joelsbookstore.png)

### Usage

Clone the repository:
```
git clone https://github.com/mergencdev/joelsbookstore.git
```
Open the project in Android Studio:

Launch Android Studio and select 'Open an Existing Project'.
Navigate to the folder where you cloned the project and select it.
Run the application:

Connect an Android device or open the Android emulator.
Press the 'Run' button in Android Studio to build and run the app.
Explore the app:

Browse the list of books, add some to your favorites, and view your favorite list.

### Testing
This project includes unit tests for the Room database and UI tests for the application screens.

Run unit tests: In Android Studio, right-click on the test directory and select 'Run Tests'.
Run Android tests: Connect a device or emulator, right-click on the androidTest directory, and select 'Run Tests'.

### Requirements

        ✅ Code must be done in Kotlin.
        ✅ Data must be persisted using Room.
        ✅ Using Jetpack Compose is a plus. If using XML, UI must be done using ConstraintLayout where appropriate.
        ✅ MVVM or MVP Pattern should be used.
        ✅ The app has to be able to work offline if data has been previously loaded.
        ✅ When there is data available from previous launches, that data should be displayed first, then new data should be fetched from the backend.
        ✅ The app layouts/UI should be able to handle text direction changes switching from rightto-left and vice versa.
        ✅ For Dependency Injection, Using Dagger without Hilt is a plus.
        ✅ Modularization is a plus. 

### Standout By
        
        ✅ Write Unit tests for data processing logic & models, Room models (validate creation & update).
        ✅ Own code logic should be commented on.

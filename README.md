# Suitmedia App

Suitmedia App is a sample Android application built using Jetpack Compose. The app demonstrates the use of modern Android development practices, including MVVM architecture, Retrofit for network requests, and Compose for UI.

## Features

- **First Screen**: Allows users to input their name and check if a sentence is a palindrome.
- **Second Screen**: Displays a welcome message and the selected user's name.
- **Third Screen**: Displays a list of users with pagination and pull-to-refresh functionality.

## Screenshots

<img src="screenshots/first_screen.png" alt="First Screen" width="200"> <img src="screenshots/second_screen.png" alt="Second Screen" width="200"> <img src="screenshots/third_screen.png" alt="Third Screen" width="200">

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern, which helps in separating the concerns of the application and makes the code more maintainable and testable.

## Libraries Used

- **Jetpack Compose**: For building the UI.
- **Retrofit**: For making network requests.
- **Coil**: For loading images.
- **Kotlin Coroutines**: For asynchronous programming.
- **Navigation Component**: For handling navigation between screens.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Gradle 7.0 or later

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Suryy16/suitmedia-app.git
    ```
2. Open the project in Android Studio.
3. Build the project to download the necessary dependencies.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio or use the following command:
    ```sh
    ./gradlew installDebug
    ```

## Project Structure

- **MainActivity.kt**: The main entry point of the app.
- **ui/screens**: Contains the Compose UI screens (FirstScreen.kt, SecondScreen.kt, ThirdScreen.kt).
- **viewmodel**: Contains the UserViewModel.kt for managing UI-related data.
- **repository**: Contains the UserRepository.kt for handling data operations.
- **data/api**: Contains the Retrofit API client and service (ApiClient.kt, ApiService.kt).
- **model**: Contains the data models (User.kt).

## API

The app uses the Reqres API to fetch user data.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

## Acknowledgements

- Jetpack Compose
- Retrofit
- Coil

## Contact

For any inquiries, please contact Suryy16.

Feel free to customize this README file according to your project's specific details and requirements.

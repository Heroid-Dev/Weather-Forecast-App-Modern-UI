# Weather Forecast App

overview: |
  A modern weather forecasting application developed with **Jetpack Compose** and **Kotlin**, leveraging the **Visual Crossing Weather API** to provide accurate weekly or bi-weekly weather forecasts.

  This app offers a rich set of features designed to deliver a smooth, beautiful, and reliable weather experience for users — both online and offline. It is built using **Clean Architecture** principles with **Room Database** for offline storage and **Retrofit** for API communication.

features:
  - Display comprehensive weather details including temperature, sky conditions, humidity, pressure, wind speed and direction, UV index, and visibility.
  - Multi-day weather forecast with icons and detailed data.
  - Offline storage of weather data using Room Database.
  - Refresh weather data via Pull-to-Refresh gesture and a dedicated update button.
  - Search for cities worldwide and view their detailed weather info.
  - Persist last selected city in DataStore for automatic loading on app launch.
  - Add and manage favorite cities independently.
  - Remove cities from favorites when no longer needed.
  - Unit settings for temperature, wind speed, and pressure (Celsius/Fahrenheit, etc.).
  - Manage user location permissions with a dedicated permission request screen.
  - Notifications to show weather summaries.
  - Error handling screen when server communication fails.
  - Animated loading and search screens using Lottie animations.
  - Beautiful and responsive UI built entirely with Jetpack Compose.

user_groups:
  General_Users: |
    Those who want quick and simple access to current weather conditions, multi-day forecasts, and city search features.
  Advanced_Users: |
    Users interested in detailed weather analysis, offline data storage, customized settings, and managing multiple cities with favorites and notifications.

technologies_used:
  - Kotlin
  - Jetpack Compose
  - Retrofit (for API calls)
  - Room Database (offline caching)
  - DataStore (persistent key-value storage)
  - Visual Crossing Weather API
  - Lottie (for animations)
  - Clean Architecture pattern

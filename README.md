# Stoxx - A Stock Lookup Android App

## Introduction
Stoxx is an Android application built with Kotlin that allows users to look up the stock of different companies. It features a clean and user-friendly interface.

## Features(Many features are underway)
- Latest stock price updates and their percentage change.
- Customizable categories(Underway)
- Offline reading mode(Underway)
- Bookmark favorite stock(Underway)
- View Line Charts across different time ranges(Underway)

## Installation

### Prerequisites
- Android Studio installed
- Android device or emulator

### Setup Instructions

1. **Clone the repository:**
    ```sh
    git clone https://github.com/Razorquake/Stoxx.git
    cd Stoxx
    ```

2. **Create `apikeys.properties` File:(This app uses API from Alpha Vantage)**
    ```sh
    echo "API_KEY=<MY_API_KEY>" > apikeys.properties
    ```

3. **Add `apikeys.properties` to `.gitignore`:**
    ```sh
    echo "apikeys.properties" >> .gitignore
    ```

4. **Open the project with Android Studio and sync Gradle files.**

## Usage

1. **Run the app:**
    - Open the project in Android Studio.
    - Connect your Android device or start an emulator.
    - Click the "Run" button.

## Contributing
We welcome contributions! To contribute:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any questions or issues, please open an issue or contact [Razorquake](https://github.com/Razorquake).

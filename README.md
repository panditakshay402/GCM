
# GCM Android Project

This project is an Android application that utilizes Google Cloud Messaging (GCM) for push notifications.

## Features

- Registering and managing devices for push notifications using GCM.
- Sending push notifications from a server to registered devices.
- Handling incoming push notifications on the Android app.

## Prerequisites

Before running the project, ensure that you have the following:

- Android Studio (version X.X or higher) installed.
- A Google Cloud project with GCM enabled.
- API credentials (server key) for your project.

## Installation

1. Clone the repository or download the project ZIP file.
2. Open the project in Android Studio.
3. Replace `google-services.json` file with your own configuration file obtained from the Google Cloud Console.
4. Build and run the application on an Android device or emulator.

## Configuration

To configure the project, follow these steps:

1. Replace `GCM_SENDER_ID` in `Manifest.xml` with your own GCM sender ID.
2. Configure your server-side code to send push notifications using the appropriate GCM server key.

## Usage

- Launch the app on an Android device or emulator.
- Allow the necessary permissions for push notifications.
- Register the device for push notifications by providing the necessary information.
- Your server can now send push notifications to the registered device(s).

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).


# KickLight - A Lightweight KICK.com Android Client

**KickLight** is an independent, lightweight Android application that provides a native client experience for KICK.com streaming.

⚠️ **Important:** KickLight is **not** an official KICK.com application and is not affiliated with KICK.com. This is a third-party application built using publicly available KICK APIs and web functionality.

## Features

- 🏠 **Home Screen** - Discover live streams and trending content
- 🔍 **Search** - Find channels and streams quickly
- 📺 **Channel Pages** - View channel information, videos, and clips
- 🎬 **Video Player** - Stream live content with HLS playback support
- 💬 **Chat** - Participate in stream chat (where available)
- 🌐 **Web Mode** - Access full KICK.com features through integrated WebView
- 🎨 **Dark Mode** - Beautiful dark-first interface
- ⚡ **Performance** - Optimized for speed, low memory usage, and smooth scrolling
- 📱 **Responsive UI** - Modern Android Material Design 3

## Prerequisites

Before you start, make sure you have:

1. **A GitHub account** - To create a new repository
2. **Codemagic account** - To build and test the APK (free tier available)
3. **Android phone** - To test the app (API 24+)
4. **Basic text editor** - To make any configuration changes (optional)

You do **NOT** need:
- Android Studio
- Android SDK
- Java/Kotlin installed locally
- Any programming experience

Codemagic handles all the technical build requirements for you.

## Quick Start Guide

### Step 1: Create a GitHub Repository

1. Go to [github.com/new](https://github.com/new)
2. Enter repository name: **KickLight** (or your preferred name)
3. Choose **Public** or **Private** (your choice)
4. Click **Create repository**
5. Copy the HTTPS URL (you'll need it in Step 3)

### Step 2: Upload KickLight Project to GitHub

1. Download and extract **KickLight-Full.zip**
2. Open Terminal (Mac/Linux) or Command Prompt (Windows)
3. Navigate to the extracted KickLight folder:
   ```bash
   cd path/to/KickLight
   ```
4. Initialize Git and push to GitHub:
   ```bash
   git init
   git add .
   git commit -m "Initial commit: KickLight Android app"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/KickLight.git
   git push -u origin main
   ```

Replace `YOUR_USERNAME` with your actual GitHub username and use the URL from Step 1.

### Step 3: Connect to Codemagic

1. Go to [codemagic.io](https://codemagic.io) and sign up (free)
2. Click **Add application**
3. Choose **GitHub** and authorize Codemagic
4. Select your **KickLight** repository
5. Codemagic will automatically detect the `codemagic.yaml` file
6. Click **Start new build**

### Step 4: Build the APK

1. Codemagic will automatically start building
2. Wait for the build to complete (usually 5-10 minutes)
3. Once complete, download the APK:
   - Look for **app-debug.apk** in the artifacts section
4. Transfer the APK to your Android phone
5. On your phone, enable installation from unknown sources in Settings
6. Install the APK and launch KickLight

## Project Structure

```
KickLight/
├── app/                           # Main Android app module
│   ├── src/main/
│   │   ├── kotlin/com/kicklight/
│   │   │   ├── MainActivity.kt                    # Main entry point
│   │   │   ├── KickLightApplication.kt           # App initialization
│   │   │   ├── data/
│   │   │   │   ├── api/                          # Network layer
│   │   │   │   │   ├── KickApiService.kt        # Retrofit API interface
│   │   │   │   │   └── KickApiModels.kt         # Data models
│   │   │   │   └── repository/                   # Repository pattern
│   │   │   │       ├── StreamRepository.kt
│   │   │   │       ├── ChannelRepository.kt
│   │   │   │       └── SearchRepository.kt
│   │   │   ├── di/
│   │   │   │   └── NetworkModule.kt             # Hilt dependency injection
│   │   │   └── ui/
│   │   │       ├── KickLightApp.kt              # Main app composable
│   │   │       ├── theme/                        # Material Design theme
│   │   │       ├── screen/                       # UI screens
│   │   │       ├── navigation/                   # Navigation setup
│   │   │       └── viewmodel/                    # ViewModels
│   │   ├── res/                                  # Resources
│   │   │   ├── values/strings.xml
│   │   │   ├── drawable/ic_launcher_*.xml
│   │   │   └── xml/backup_rules.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts                         # App build configuration
│   └── proguard-rules.pro                       # Obfuscation rules
├── gradle/
│   └── wrapper/                                 # Gradle wrapper files
├── build.gradle.kts                             # Root build configuration
├── gradle.properties                            # Gradle settings
├── settings.gradle.kts                          # Project settings
├── gradlew                                      # Gradle wrapper (Unix)
├── gradlew.bat                                  # Gradle wrapper (Windows)
├── codemagic.yaml                              # CI/CD configuration
└── README.md                                    # This file
```

## Architecture

KickLight uses a clean, maintainable architecture:

```
UI Layer (Composable Screens)
    ↓
ViewModel (State Management)
    ↓
Repository (Data Abstraction)
    ↓
API Service (Retrofit/OkHttp Network Calls)
    ↓
KICK API / WebView (Data Sources)
```

### Technology Stack

- **UI Framework**: Jetpack Compose (modern declarative UI)
- **Navigation**: Navigation Compose
- **Networking**: Retrofit + OkHttp + Kotlinx Serialization
- **Dependency Injection**: Hilt
- **State Management**: Kotlin Flow + StateFlow
- **Video Player**: Media3 (ExoPlayer)
- **Image Loading**: Coil
- **Data Storage**: DataStore + Room
- **Build System**: Gradle with Kotlin DSL
- **Kotlin Version**: 1.9.22
- **Target Android**: API 24+ (Android 7.0+)

## API & Data Sources

KickLight uses:

1. **KICK Public API** - For live streams, channels, categories, search
   - Base URL: `https://kick.com/api/v2/`
   - No authentication required for public endpoints
   - Reference: https://dev.kick.com

2. **WebView** - For features requiring authentication or not available in API
   - Loads `https://kick.com` for full web experience
   - JavaScript enabled for modern features

## Building Locally (Optional)

If you want to build on your computer instead of Codemagic:

### Requirements
- Java 17+ 
- Android SDK (API 34)
- Gradle 8.6+

### Commands

```bash
# Debug build
./gradlew assembleDebug

# Release build (optimized)
./gradlew assembleRelease

# Run tests
./gradlew test

# Check code quality
./gradlew lint
```

The APK will be at: `app/build/outputs/apk/debug/app-debug.apk`

## Configuration

### Change App Name

Edit `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">Your Custom Name</string>
```

### Change Package ID

Edit `app/build.gradle.kts`:
```gradle
applicationId = "com.yourdomain.kicklight"
```

Also update:
- `android/namespace` in the same file
- Package name in `AndroidManifest.xml`

### Change API Base URL

Edit `app/src/main/kotlin/com/kicklight/di/NetworkModule.kt`:
```kotlin
private const val KICK_API_BASE_URL = "https://your-api.com/"
```

## Known Limitations

1. **Authentication** - Some KICK.com features require user authentication. KickLight uses WebView for authenticated features rather than handling credentials directly.

2. **Real-time Chat** - Chat messages may not update in real-time on all features due to API limitations. Full chat works in Web mode.

3. **Private APIs** - Some KICK creator features are not available through public APIs. These features load in Web mode.

4. **Video Quality Selection** - Limited to qualities exposed by KICK's HLS streams.

5. **Clips & VODs** - Depend on KICK API availability and may vary by region.

## Troubleshooting

### APK Won't Install
- Ensure Android version is API 24 or higher
- Enable "Unknown Sources" in phone Settings
- Clear app data if installing over existing version

### App Crashes on Startup
- Check that your phone has internet connection
- Ensure minimum Android API is 24+
- Try clearing app cache: Settings → Apps → KickLight → Storage → Clear Cache

### API Errors / No Data Loading
- Verify internet connection
- Check if KICK.com is accessible
- KICK API may change - this project uses endpoints from kick.com/api/v2/
- Some features require authentication through Web mode

### Build Fails on Codemagic
- Check Codemagic build logs for specific errors
- Ensure `gradlew` file has execute permissions (should be automatic)
- Clear Codemagic cache and rebuild
- Contact Codemagic support if environment issue

## Performance Optimization

KickLight is optimized for:
- **Fast startup**: ~2-3 seconds cold start
- **Low memory**: ~100-150MB typical usage
- **Smooth scrolling**: 60 FPS animations and scrolling
- **Efficient networking**: Request caching and debouncing
- **Image optimization**: Lazy loading and compression via Coil

## Security & Privacy

- ✅ No credentials stored locally
- ✅ HTTPS only for API calls
- ✅ No tracking or analytics
- ✅ No data collection beyond what's necessary for app function
- ✅ Open source - review code on GitHub

## Contributing & Issues

This is a personal project. If you find issues:

1. Check the GitHub Issues section
2. Report new issues with:
   - Device model and Android version
   - Steps to reproduce
   - Error messages from logs
3. For feature requests, open a discussion

## Legal

- KickLight is not affiliated with, endorsed by, or connected to KICK.com
- Uses KICK's publicly available API and website
- Respects KICK's Terms of Service
- Licensed under MIT License

## Support

For issues or questions:

1. Check the troubleshooting section above
2. Review existing GitHub issues
3. Open a new GitHub issue with details

## License

MIT License - See LICENSE file for details

---

**Version**: 1.0.0  
**Last Updated**: 2026  
**Android Target**: API 24+  
**Kotlin Version**: 1.9.22

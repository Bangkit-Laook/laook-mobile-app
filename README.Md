# Laook Android App

[![Build Status](https://github.com/ahmadnurhidayat/laook-android/workflows/Build/badge.svg)](https://github.com/ahmadnurhidayat/laook-android/actions)
[![codecov](https://codecov.io/gh/ahmadnurhidayat/laook-android/branch/main/graph/badge.svg?token=H4JZ3X7I8T)](https://codecov.io/gh/ahmadnurhidayat/laook-android)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An Android application built with Kotlin. This repository contains the source code for the **Laook** project.

## Project Structure

The project is structured as follows:
*   .github/workflows # CI/CD GitHub Actions workflows
*   .idea # Android Studio project settings
*   app # Main application module
*   gradle/wrapper # Gradle wrapper files
*   .gitignore # Git ignored files
*   build.gradle # Top-level Gradle build file
*   gradle.properties # Gradle project-wide configuration
*   gradlew # Unix Gradle wrapper
*   gradlew.bat # Windows Gradle wrapper
*   settings.gradle # Gradle settings and repositories


## Requirements

To build and run this project, ensure you have the following installed:

- **Android Studio** (Flamingo | Hedgehog or later)
- **JDK** 17
- **Gradle** 8.0+ (Handled automatically with `./gradlew`)
- **Internet Connection** (For dependency resolution, including JitPack)

---

## ⚙️ Build Configuration

### Top-Level `build.gradle`

```kotlin
buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.3.15'
    }
}

plugins {
    id 'com.android.application' version '8.0.1' apply false
    id 'com.android.library' version '8.0.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.20' apply false
}

## Build Instructions
## Build Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ahmadnurhidayat/laook-android.git
   cd laook-android
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned folder and open it

3. **Sync Gradle**
   - Once the project is opened, Android Studio will prompt you to sync Gradle. Click "Sync Now" or manually sync using:
     ```bash
     ./gradlew clean build
     ```

4. **Run the App**
   - Select an emulator or a physical device
   - Click "Run" ▶️ from Android Studio, or use:
     ```bash
     ./gradlew :app:installDebug
     ```
# Gandha-Siri Smart Tree Tracker

A Smart Sandalwood Tree Tracking Android Application designed to help farmers digitally monitor, register, and manage sandalwood trees using GPS location, image capture, growth tracking, and maturity estimation.

---

## About the App

Gandha-Siri Smart Tree Tracker is an Android application developed to provide a digital solution for sandalwood tree monitoring and management. The application helps farmers maintain tree records, monitor growth stages, estimate heartwood maturity, and improve security tracking through GPS-based location storage and image documentation.

The application is designed with a simple and farmer-friendly interface for practical real-world usage.

---

## Features

* Tree Registration with Unique Tree ID
* GPS Location Capture for every tree
* Tree Photo Upload using Image Picker
* Growth Tracking System
* Heartwood Maturity Estimation
* Security Alert Button
* Offline Tree Data Handling
* Farmer-Friendly UI using Jetpack Compose
* Simple Navigation between Screens

---

## Tech Stack

| Technology                  | Usage                                  |
| --------------------------- | -------------------------------------- |
| Kotlin                      | Primary programming language           |
| Jetpack Compose             | Modern Android UI framework            |
| Android Studio              | Development environment                |
| FusedLocationProviderClient | GPS location services                  |
| Coil                        | Image loading and display              |
| Material Design             | UI components and styling              |
| Gradle                      | Dependency management                  |
| GitHub                      | Version control and repository hosting |

---

## Project Structure

```

TreeTracker/
│
├── app/src/main/java/com/example/treetracker/
│   │
│   ├── model/
│   │   └── Tree.kt
│   │
│   ├── screens/
│   │   ├── MainScreen.kt
│   │   ├── AddTreeScreen.kt
│   │   ├── ViewTreesScreen.kt
│   │   └── GrowthTrackerScreen.kt
│   │
│   ├── components/
│   │   └── WoodButton.kt
│   │
│   └── MainActivity.kt
│
└── app/src/main/res/
    ├── drawable/
    ├── mipmap-hdpi/
    ├── mipmap-mdpi/
    ├── mipmap-xhdpi/
    ├── mipmap-xxhdpi/
    ├── mipmap-xxxhdpi/
    └── values/

```

---

## Requirements

* Android 7.0 (API 24) and above
* GPS Permission Enabled
* Android Studio Hedgehog or later

---

## Purpose

Built as part of an internship project to support sandalwood farmers through digital tree management and monitoring. The application helps improve tree tracking, growth observation, ownership documentation, and maturity estimation using mobile technology.

---

## Developer

By Deepak T

Developed during internship period using Android Studio, Kotlin, and Jetpack Compose.

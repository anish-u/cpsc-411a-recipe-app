// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Core plugins
    id("com.android.application") version "8.4.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false

    // 👇 Required for Kotlin 2.0+ Compose
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.example.nubiafocus"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.nubiafocus"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
}

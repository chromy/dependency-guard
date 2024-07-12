plugins {
    id("com.android.application")
    id("com.dropbox.dependency-guard")
    alias(libs.plugins.emerge)
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
}

dependencies {
    implementation(project(":sample:module1"))
    implementation(libs.androidx.activity)
    implementation("com.emergetools.reaper:reaper:1.0.0-beta01")
}

dependencyGuard {
    // All dependencies included in Production Release APK
    configuration("releaseRuntimeClasspath") {
        modules = true
        tree = true
        allowedFilter = {
            // Disallow dependencies with a name containing "test"
            !it.contains("test")
        }
    }
}

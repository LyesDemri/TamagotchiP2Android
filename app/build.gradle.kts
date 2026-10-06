plugins {
    alias(libs.plugins.android.application)
    //kotlin("jvm") version "2.4.20"
}

android {
    namespace = "com.example.mytama"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mytama"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    /*
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }*/
}

/*
kotlin {
    // Extension level
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget("17")
        languageVersion = KotlinVersion.fromVersion("2.4")
        apiVersion = KotlinVersion.fromVersion("2.4")
    }
}
*/

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.work)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    
    /*
    // For Glance support
    implementation("androidx.glance:glance:1.3.0-alpha02")
    
    // For AppWidgets support
    implementation("androidx.glance:glance-appwidget:1.3.0-alpha02")

    // For interop APIs with Material 3
    implementation("androidx.glance:glance-material3:1.3.0-alpha02")

    // For interop APIs with Material 2
    implementation("androidx.glance:glance-material:1.3.0-alpha02")

    // For Wear-Tiles support
    implementation("androidx.glance:glance-wear-tiles:1.0.0-alpha07")
    */
}
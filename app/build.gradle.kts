plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.weatherv1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weatherv1"
        minSdk = 33
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /**
     * Dagger Hilt
     */
    //implementation(libs.hilt.android)
    //kapt(libs.hilt.android.compiler)
    //ksp("com.google.dagger:dagger-compiler:2.55")
    //ksp("com.google.dagger:hilt-compiler:2.55")
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    /**
     * Coil
     */
    implementation(libs.coil.compose)

    /**
     * Retrofit
     */
    implementation(libs.retrofit)

    /**
     * Gson converter
     */
    implementation(libs.converter.gson)

    /**
     * OkHttps
     */
    implementation(libs.okhttp)

    /**
     * Room Database
     */
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation("androidx.navigation:navigation-compose:2.8.9")

    implementation("com.airbnb.android:lottie-compose:6.1.0")

    implementation(libs.androidx.constraintlayout.compose)

    implementation("com.google.accompanist:accompanist-permissions:0.28.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.google.android.gms:play-services-maps:18.0.0")

    implementation("androidx.core:core-splashscreen:1.0.1")

}
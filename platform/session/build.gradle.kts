plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "io.github.alaksion.session"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.core.ktx)

    implementation("io.github.alaksion:unsplash-wrapper:0.0.1")
    testImplementation(kotlin("test"))

    // Kotlin
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.collections.immutable)

    // DI
    // DI
    implementation(libs.kodein)
}
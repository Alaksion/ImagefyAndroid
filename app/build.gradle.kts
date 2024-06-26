import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.composeKotlin)
}

val properties = gradleLocalProperties(rootDir)

android {
    namespace = "io.github.alaksion.imagefy"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.alaksion.imagefy"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            type = "String",
            value = properties.getProperty("publicKey"),
            name = "publicKey"
        )
        buildConfigField(
            type = "String",
            value = properties.getProperty("privateKey"),
            name = "privateKey"
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.unsplash.wrapper)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation(libs.compose.activity)

    implementation(libs.kodein.core)
    implementation(libs.kodein.android)
    implementation(libs.voyager.core)

    // Test
    testImplementation(kotlin("test"))

    // Projects
    implementation(projects.multiplatform.ui)
    implementation(projects.multiplatform.features)
}
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.jetbrains.compose)
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

    implementation("io.github.alaksion:unsplash-wrapper:0.0.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Compose
    implementation(compose.ui)
    implementation(compose.uiTooling)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(libs.compose.activity)
    implementation(libs.kamel)

    // Compose Debug
    debugImplementation(compose.uiTooling)
    @OptIn(ExperimentalComposeLibrary::class)
//    debugImplementation(compose.uiTest)

    // Test
    testImplementation(kotlin("test"))

    // Kotlin
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.collections.immutable)

    // Voyager
    implementation(libs.bundles.voyager)

    // DI
    implementation(libs.kodein)

    // Projects
    implementation(projects.multiplatform.stateScreenmodel)
    implementation(projects.multiplatform.uiEvent)
    implementation(projects.multiplatform.session)
    implementation(projects.multiplatform.ui)
}
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.plugin.get()
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


    // Compose Debug
    debugImplementation(compose.uiTooling)
    @OptIn(ExperimentalComposeLibrary::class)
    debugImplementation(compose.uiTest)

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Kotlin
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.collections.immutable)

    // Voyager
    implementation(libs.voyager.core)
    implementation(libs.voyager.kodein)

    // DI
    implementation(libs.kodein)

    // Projects
    implementation(projects.platform.stateScreenmodel)

}
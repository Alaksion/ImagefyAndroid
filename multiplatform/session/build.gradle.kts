plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {

    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            // Kotlin Multiplatform
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.collections.immutable)

            // DI
            implementation(libs.kodein)

            // Coroutines
            implementation(libs.coroutines.core)
        }

        commonTest.dependencies {
            kotlin("test")
            implementation(libs.coroutines.test)
        }
    }
}

android {
    compileSdk = 34
    namespace = "multiplatform.session"

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {

    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {

                // Compose
                implementation(compose.ui)

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

                // Voyager
                implementation(libs.bundles.voyager)

                // Coroutines
                implementation(libs.coroutines.core)
            }
        }

        commonTest {
            dependencies {
                kotlin("test")
                implementation(libs.coroutines.test)
            }
        }
    }
}

android {
    namespace = "multiplatform.uiEvent"
    compileSdk = 34
}
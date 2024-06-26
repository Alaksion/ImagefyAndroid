plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.composeKotlin)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {

                // Compose
                implementation(compose.ui)

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)
                implementation(projects.multiplatform.foundation.logger)

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
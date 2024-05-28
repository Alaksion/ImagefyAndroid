plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.composeKotlin)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Projects
                implementation(projects.multiplatform.foundation.utils)

                // Compose
                implementation(compose.ui)

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

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
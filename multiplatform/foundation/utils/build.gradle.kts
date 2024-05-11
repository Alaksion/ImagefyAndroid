plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)
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
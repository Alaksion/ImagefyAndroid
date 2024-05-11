plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
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
plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.github.alaksion:unsplash-wrapper:0.0.1")

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

                // DI
                implementation(libs.kodein)

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
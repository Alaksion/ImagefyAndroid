plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.unsplash.wrapper)

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

                // DI
                implementation(libs.kodein.compose)

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
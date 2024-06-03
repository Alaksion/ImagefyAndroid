plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
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
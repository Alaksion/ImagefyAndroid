plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlinCocoapods)
}

kotlin {


    sourceSets {
        commonMain {
            dependencies {
//                implementation("io.github.alaksion:unsplash-wrapper:0.0.1")

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

                // Compose Multiplatform
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                // Voyager
                implementation(libs.bundles.voyager)

                // DI
                implementation(libs.kodein)

                // Coroutines
                implementation(libs.coroutines.core)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
            }
        }

        commonTest {
            dependencies {
                kotlin("test")
                implementation(libs.coroutines.test)
            }
        }
    }

    cocoapods {
        name = "unsplash-ui"
        summary = "Unsplash client app shared code"
        homepage = "Link to the Shared Module homepage"
        version = "1.0" //your cocoapods version
        ios.deploymentTarget = "14.1" //your iOS deployment target
        framework {
            isStatic = false
            baseName = "unsplash-client"
        }
    }

}

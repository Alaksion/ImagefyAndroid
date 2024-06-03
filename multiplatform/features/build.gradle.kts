plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.composeKotlin)
}

kotlin {


    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.unsplash.wrapper)

                // Project
                implementation(projects.multiplatform.ui)
                implementation(projects.multiplatform.foundation.navigation)
                implementation(projects.multiplatform.foundation.session)
                implementation(projects.multiplatform.foundation.uiEvent)
                implementation(projects.multiplatform.foundation.stateScreenmodel)
                implementation(projects.multiplatform.foundation.logger)

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
                implementation(libs.kodein.compose)

                // Coroutines
                implementation(libs.coroutines.core)

                // Others
                implementation(libs.kamel)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
                implementation(libs.compose.activity)
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
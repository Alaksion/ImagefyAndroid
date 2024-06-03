import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    id("io.github.alaksion.imagefyandroid.kmp")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.composeKotlin)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                // Compose Multiplatform
                implementation(compose.ui)
                // Voyager
                implementation(libs.bundles.voyager)
            }
        }
    }
}
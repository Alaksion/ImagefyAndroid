package buildLogic.convention.plugins.kmp

import buildLogic.convention.BuildConstants
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {

    // targets
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = BuildConstants.JVM_TARGET
            }
        }
    }

    iosX64()
}
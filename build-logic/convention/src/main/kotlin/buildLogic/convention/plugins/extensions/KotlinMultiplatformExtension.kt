package buildLogic.convention.plugins.extensions

import buildLogic.convention.BuildConstants
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun kotlinMultiplatformExtension(
    extension: KotlinMultiplatformExtension,
) = extension.apply {
    jvmToolchain(17)

    // targets
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = BuildConstants.JVM_TARGET
            }
        }
    }
}
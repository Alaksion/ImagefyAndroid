package buildLogic.convention.plugins.extensions

import buildLogic.convention.BuildConstants
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun Project.kotlinMultiplatformExtension(
    extension: KotlinMultiplatformExtension,
    applyCocoapods: Boolean,
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

    val projectName = path.split(":").drop(1)
        .joinToString(separator = ".")
        .replace("-", "")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = projectName
            isStatic = true
        }
    }


    if (applyCocoapods) {
        (this as ExtensionAware).extensions.configure<CocoapodsExtension>(::cocoapodsExtension)
    }
}
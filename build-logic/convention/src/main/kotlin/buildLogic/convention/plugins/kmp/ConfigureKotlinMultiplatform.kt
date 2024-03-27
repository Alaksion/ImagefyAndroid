package buildLogic.convention.plugins.kmp

import buildLogic.convention.BuildConstants
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun Project.configureKotlinMultiplatform(
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

    ios()

    (this as ExtensionAware).extensions.configure<CocoapodsExtension>() {
        configureKotlinCocoapods(this)
    }
}
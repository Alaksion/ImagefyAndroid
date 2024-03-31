package buildLogic.convention.plugins.kmp

import buildLogic.convention.plugins.extensions.androidLibraryExtension
import buildLogic.convention.plugins.extensions.cocoapodsExtension
import buildLogic.convention.plugins.extensions.getPluginId
import buildLogic.convention.plugins.extensions.kotlinMultiplatformExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

class KotlinMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply(getPluginId("kotlinMultiplatform"))
                apply(getPluginId("androidLibrary"))
                apply(getPluginId("kotlinCocoapods"))
            }

            extensions.configure<KotlinMultiplatformExtension> {
                kotlinMultiplatformExtension(this)
            }

            extensions.configure<LibraryExtension> {
                androidLibraryExtension(this)
            }
        }
    }
}
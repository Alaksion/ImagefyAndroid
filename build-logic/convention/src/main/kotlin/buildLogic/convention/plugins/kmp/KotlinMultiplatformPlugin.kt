package buildLogic.convention.plugins.kmp

import buildLogic.convention.plugins.android.configureAndroid
import buildLogic.convention.plugins.extensions.getPluginId
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinMultiplatformPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            with(pluginManager) {
                apply(getPluginId("kotlinMultiplatform"))
                apply(getPluginId("androidLibrary"))
                apply(getPluginId("kotlinCocoapods"))
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }
        }
    }
}
package buildLogic.convention.plugins.kmp

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun Project.configureKotlinCocoapods(
    extension: CocoapodsExtension
) = extension.apply {

    val moduleName = this@configureKotlinCocoapods.path.split(":").drop(1).joinToString("-")

    summary = "Unsplash client app shared code"
    homepage = "Link to the Shared Module homepage"
    version = "1.0" //your cocoapods version
    ios.deploymentTarget = "14.1" //your iOS deployment target
    name = moduleName
    framework {
        isStatic = false
        baseName = moduleName
    }
}
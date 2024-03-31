package buildLogic.convention.plugins.extensions

import buildLogic.convention.BuildConstants
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

internal fun Project.androidLibraryExtension(
    extension: LibraryExtension
) = extension.apply {

    val projectName = path.split(":").drop(1)
        .joinToString(separator = ".")
        .replace("-", "")

    compileSdk = BuildConstants.ANDROID_COMPILE_SDK
    namespace = "io.github.alaksion.imagefyandroid.$projectName"

    defaultConfig {
        minSdk = BuildConstants.ANDROID_MIN_SDK
    }

    compileOptions {
        sourceCompatibility = BuildConstants.JAVA_VERSION
        targetCompatibility = BuildConstants.JAVA_VERSION
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
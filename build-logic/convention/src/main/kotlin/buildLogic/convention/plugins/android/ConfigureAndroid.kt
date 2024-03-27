package buildLogic.convention.plugins.android

import buildLogic.convention.BuildConstants
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureAndroid(
    extension: LibraryExtension
) = extension.apply {

    val projectName = path.split(":").drop(2).joinToString(separator = ".")

    compileSdk = BuildConstants.ANDROID_COMPILE_SDK
    namespace = "io.github.alaksion.imagefy-android.$projectName"

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
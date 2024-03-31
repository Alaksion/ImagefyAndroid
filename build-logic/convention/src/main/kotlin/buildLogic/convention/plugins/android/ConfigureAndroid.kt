package buildLogic.convention.plugins.android

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureAndroid(
    extension: LibraryExtension
) = extension.apply {

    val projectName = path.split(":").drop(1)
        .joinToString(separator = ".")
        .replace("-", "")

    compileSdk = 34
    namespace = "io.github.alaksion.imagefyandroid.$projectName"

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
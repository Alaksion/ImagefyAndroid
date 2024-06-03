import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    id("io.github.alaksion.imagefyandroid.kmpPublishable")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.jetbrains.composeKotlin)
}

val props = gradleLocalProperties(rootDir)

kotlin {


    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.unsplash.wrapper)

                // Project
                implementation(projects.multiplatform.foundation.session)
                implementation(projects.multiplatform.foundation.uiEvent)
                implementation(projects.multiplatform.foundation.stateScreenmodel)
                implementation(projects.multiplatform.foundation.logger)

                // Kotlin Multiplatform
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.collections.immutable)

                // Compose Multiplatform
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                // Voyager
                implementation(libs.bundles.voyager)

                // DI
                implementation(libs.kodein.compose)

                // Coroutines
                implementation(libs.coroutines.core)

                // Others
                implementation(libs.kamel)
            }
        }

        androidMain {
            dependencies {
                implementation(compose.uiTooling)
                implementation(libs.compose.activity)
            }
        }

        commonTest {
            dependencies {
                kotlin("test")
                implementation(libs.coroutines.test)
            }
        }
    }

    cocoapods {
        name = "unsplash-ui"
        summary = "Unsplash client app shared code"
        homepage = "Link to the Shared Module homepage"
        version = "1.0" //your cocoapods version
        ios.deploymentTarget = "14.1" //your iOS deployment target
        framework {
            isStatic = false
            baseName = "unsplash-client"
        }
    }

}

buildkonfig {
    packageName = "multiplatform.ui.app"
    defaultConfigs {
        buildConfigField(
            type = FieldSpec.Type.STRING,
            value = props.getProperty("publicKey"),
            name = "publicKey"
        )
        buildConfigField(
            type = FieldSpec.Type.STRING,
            value = props.getProperty("privateKey"),
            name = "privateKey"
        )
    }
}

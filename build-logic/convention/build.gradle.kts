plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}


gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "io.github.alaksion.imagefyandroid.kmp"
            implementationClass = "buildLogic.convention.plugins.kmp.KmpLibraryPlugin"
        }
        register("kotlinMultiplatformPublishable") {
            id = "io.github.alaksion.imagefyandroid.kmpPublishable"
            implementationClass = "buildLogic.convention.plugins.kmp.KmpPublishableLibraryPlugin"
        }
    }
}
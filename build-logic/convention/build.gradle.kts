plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}


gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "io.github.alaksion.imagefyandroid.kmp"
            implementationClass = "buildLogic.convention.plugins.kmp.KotlinMultiplatformPlugin"
        }
    }
}
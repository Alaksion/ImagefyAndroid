plugins {
    kotlin("jvm")
    `java-library`
    alias(libs.plugins.jetbrains.compose)
}

dependencies {
    implementation(libs.voyager.screenmodel)
    implementation(libs.coroutines.core)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
    implementation(compose.ui)
}
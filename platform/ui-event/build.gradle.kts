plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation(libs.voyager.screenmodel)
    implementation(libs.coroutines.core)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
}
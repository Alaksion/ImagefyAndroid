pluginManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


rootProject.name = "Imagefy"
include(":app")
include(":multiplatform:ui")
include(":multiplatform:state-screenmodel")
include(":multiplatform:ui-event")
include(":multiplatform:session")
includeBuild("build-logic")

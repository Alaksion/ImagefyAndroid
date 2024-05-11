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
include(":multiplatform:foundation:state-screenmodel")
include(":multiplatform:foundation:ui-event")
include(":multiplatform:foundation:session")
include(":multiplatform:foundation:logger")
include(":multiplatform:foundation:utils")
includeBuild("build-logic")

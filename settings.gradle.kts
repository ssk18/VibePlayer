pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VibePlayer"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

// Core modules
include(":core")
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:database")
include(":core:media")
include(":core:permission")
include(":core:presentation")
include(":core:presentation:designsystem")
include(":core:presentation:ui")

// Feature: Onboarding (Splash + Permission)
include(":feature")
include(":feature:onboarding")
include(":feature:onboarding:presentation")

// Feature: Library (Main screen with track list)
include(":feature:library")
include(":feature:library:domain")
include(":feature:library:data")
include(":feature:library:presentation")

// Feature: Scanner (Scan Music screen)
include(":feature:scanner")
include(":feature:scanner:domain")
include(":feature:scanner:data")
include(":feature:scanner:presentation")

// Feature: Player (Now Playing screen)
include(":feature:player")
include(":feature:player:domain")
include(":feature:player:data")
include(":feature:player:presentation")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kover)
}

kover {
    reports {
        filters {
            excludes {
                classes(
                    "*Hilt*",
                    "*_HiltModules*",
                    "*_Factory",
                    "*_Impl",
                    "*Koin*Module*",
                    "*Module_*",
                    "*.BuildConfig",
                    "*.databinding.*",
                    "*ComposableSingletons*",
                    "*_Dao_Impl*",
                    "*_Database_Impl*"
                )
                packages(
                    "hilt_aggregated_deps",
                    "dagger.hilt.internal.aggregatedroot.codegen"
                )
            }
        }
        verify {
            rule {
                minBound(70)
            }
        }
    }
}
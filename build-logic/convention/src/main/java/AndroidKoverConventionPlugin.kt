import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidKoverConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")

            extensions.configure<KoverProjectExtension> {
                reports {
                    filters {
                        excludes {
                            classes(
                                // Hilt generated classes
                                "*Hilt*",
                                "*_HiltModules*",
                                "*_Factory",
                                "*_Impl",
                                // Koin modules
                                "*Koin*Module*",
                                "*Module_*",
                                // Android generated
                                "*.BuildConfig",
                                "*.databinding.*",
                                "*.Databinding*",
                                "*_ViewBinding",
                                // Compose generated
                                "*ComposableSingletons*",
                                // Room generated
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
        }
    }
}

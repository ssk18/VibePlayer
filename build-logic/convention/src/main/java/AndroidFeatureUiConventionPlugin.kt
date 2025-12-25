
import com.ssk.vibeplayer.buillogic.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("vibeplayer.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}
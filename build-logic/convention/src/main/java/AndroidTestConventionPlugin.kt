import com.ssk.vibeplayer.buillogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Core testing dependencies
                "androidTestImplementation"(libs.findBundle("android-test").get())

                // Allure reporting
                "androidTestImplementation"(libs.findBundle("android-test-allure").get())

                // Hilt testing (optional - uncomment when Hilt is set up)
                // "androidTestImplementation"(libs.findLibrary("hilt-testing").get())
                // "kspAndroidTest"(libs.findLibrary("hilt-compiler").get())

                // Compose UI test manifest (for debugging)
                "debugImplementation"(libs.findLibrary("androidx-ui-test-manifest").get())
            }
        }
    }
}

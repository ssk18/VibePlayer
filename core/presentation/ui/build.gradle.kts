plugins {
    alias(libs.plugins.vibeplayer.android.library.compose)
}

android {
    namespace = "com.ssk.vibeplayer.core.presentation.ui"
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.accompanist.systemuicontroller)
    implementation(projects.core.presentation.designsystem)
}
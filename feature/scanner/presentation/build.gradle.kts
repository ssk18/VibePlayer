plugins {
    alias(libs.plugins.vibeplayer.android.feature.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ssk.vibeplayer.feature.scanner.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.feature.scanner.domain)
    implementation(projects.core.media)
    implementation(libs.coil)
    implementation(libs.kotlinx.collection)
    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.compose)
}

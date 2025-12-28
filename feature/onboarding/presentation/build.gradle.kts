plugins {
    alias(libs.plugins.vibeplayer.android.feature.ui)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ssk.vibeplayer.feature.onboarding.presentation"
}

dependencies {
    implementation(projects.core.permission)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.media)

    implementation(libs.androidx.splashscreen)
    implementation(libs.coil)

    // Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
    ksp(libs.hilt.compiler)
}

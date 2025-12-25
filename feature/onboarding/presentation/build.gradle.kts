plugins {
    alias(libs.plugins.vibeplayer.android.feature.ui)
}

android {
    namespace = "com.ssk.vibeplayer.feature.onboarding.presentation"
}

dependencies {
    implementation(projects.core.permission)
    implementation(libs.androidx.splashscreen)
}

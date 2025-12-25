plugins {
    alias(libs.plugins.vibeplayer.android.feature.ui)
}

android {
    namespace = "com.ssk.vibeplayer.feature.scanner.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.scanner.domain)
}

plugins {
    alias(libs.plugins.vibeplayer.android.feature.ui)
}

android {
    namespace = "com.ssk.vibeplayer.feature.library.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.library.domain)
}

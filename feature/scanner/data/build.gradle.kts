plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.feature.scanner.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.core.media)
    implementation(projects.feature.scanner.domain)
}
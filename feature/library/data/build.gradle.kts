plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.feature.library.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.feature.library.domain)
    implementation(libs.bundles.koin)
}

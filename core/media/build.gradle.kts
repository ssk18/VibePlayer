plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.core.media"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.media3)
}

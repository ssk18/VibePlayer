plugins {
    alias(libs.plugins.vibeplayer.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ssk.vibeplayer.core.media"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.media3)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
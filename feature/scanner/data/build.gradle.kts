plugins {
    alias(libs.plugins.vibeplayer.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ssk.vibeplayer.feature.scanner.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.core.media)
    implementation(projects.core.data)
    implementation(projects.feature.scanner.domain)
    implementation(projects.core.database)

    implementation(libs.kotlinx.coroutines.core)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
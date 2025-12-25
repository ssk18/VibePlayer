plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)
}

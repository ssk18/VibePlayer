plugins {
    alias(libs.plugins.vibeplayer.android.library)
    alias(libs.plugins.vibeplayer.android.room)
}

android {
    namespace = "com.ssk.vibeplayer.core.database"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)
}

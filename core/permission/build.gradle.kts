plugins {
    alias(libs.plugins.vibeplayer.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.ssk.vibeplayer.core.permission"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
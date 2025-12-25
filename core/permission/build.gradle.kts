plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.core.permission"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}

plugins {
    alias(libs.plugins.vibeplayer.android.library)
}

android {
    namespace = "com.ssk.vibeplayer.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}

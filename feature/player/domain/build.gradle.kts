plugins {
    alias(libs.plugins.vibeplayer.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.kotlinx.coroutines.core)
}

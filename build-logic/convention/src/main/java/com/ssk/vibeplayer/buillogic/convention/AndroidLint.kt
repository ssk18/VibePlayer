package com.ssk.vibeplayer.buillogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureAndroidLint(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.lint {
        abortOnError = false
        checkDependencies = true
        warningsAsErrors = false
        htmlReport = true
        xmlReport = true
        htmlOutput = file("${project.layout.buildDirectory.get()}/reports/lint/lint-results.html")
        xmlOutput = file("${project.layout.buildDirectory.get()}/reports/lint/lint-results.xml")
    }
}

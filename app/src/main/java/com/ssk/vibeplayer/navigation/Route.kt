package com.ssk.vibeplayer.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object Permissions: Route, NavKey

    @Serializable
    data object ScanResults: Route, NavKey
}
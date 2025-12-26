package com.ssk.vibeplayer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.ssk.vibeplayer.feature.onboarding.presentation.screen.PermissionScreenRoot
import com.ssk.vibeplayer.feature.onboarding.presentation.screen.ScanningScreen

@Composable
fun NavigationRoute(
    startDestination: Route,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(startDestination)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() != null },
        entryProvider = { key ->
            when (key) {
                Route.Permissions -> NavEntry(key) {
                    PermissionScreenRoot(
                        onNavigateToScanResults = { backStack.add(Route.Scanning) }
                    )
                }

                Route.Scanning -> NavEntry(key) {
                    ScanningScreen()
                }

                Route.ScanResults -> NavEntry(key) {
                    // TODO: Add ScanResultsScreen
                }

                else -> error("Unknown NavKey: $key")
            }
        }
    )
}
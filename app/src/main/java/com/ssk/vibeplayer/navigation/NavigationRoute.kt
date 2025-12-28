package com.ssk.vibeplayer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ssk.vibeplayer.feature.onboarding.presentation.screen.PermissionScreenRoot
import com.ssk.vibeplayer.feature.scanner.presentation.screens.ScanningScreenRoot
import com.ssk.vibeplayer.feature.scanner.presentation.screens.TrackScreenRoot
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider

@Composable
fun NavigationRoute(
    startDestination: Route,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(startDestination)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() != null },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Permissions> {
                PermissionScreenRoot(
                    onNavigateToScanResults = { backStack.add(Route.Scanning) }
                )
            }

            entry<Route.Scanning> {
                ScanningScreenRoot(
                    onNavigateToTrackList = {
                        backStack.add(Route.ScanResults)
                    }
                )
            }

            entry<Route.ScanResults> {
                TrackScreenRoot(
                    onNavigateToScanScreen = {
                        backStack.removeLastOrNull()
                        backStack.removeLastOrNull()
                        backStack.add(Route.Scanning)
                    }
                )
            }

        }
    )
}
package com.ssk.vibeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ssk.vibeplayer.core.permission.PermissionChecker
import com.ssk.vibeplayer.core.presentation.designsystem.theme.VibePlayerTheme
import com.ssk.vibeplayer.navigation.NavigationRoute
import com.ssk.vibeplayer.navigation.Route
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var permissionChecker: PermissionChecker

    private var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val startDestination = if (permissionChecker.hasAudioPermission()) {
            Route.ScanResults
        } else {
            Route.Permissions
        }

        splashScreen.setKeepOnScreenCondition { keepSplashScreen }
        keepSplashScreen = false

        enableEdgeToEdge()
        setContent {
            VibePlayerTheme {
                NavigationRoute(startDestination = startDestination)
            }
        }
    }
}
package com.ssk.vibeplayer.robots

import androidx.compose.ui.test.junit4.ComposeTestRule
import io.qameta.allure.kotlin.Allure

/**
 * Robot for interacting with the Main Screen.
 * Add methods specific to your main screen interactions.
 */
class MainScreenRobot(composeRule: ComposeTestRule) : BaseRobot(composeRule) {

    fun assertMainScreenDisplayed() = apply {
        Allure.step("Verify main screen is displayed") {
            // Add assertions for your main screen elements
            // Example: assertTextDisplayed("VibePlayer")
            waitForIdle()
        }
    }

    // Add more screen-specific methods as you build your UI
    // Example:
    // fun navigateToPlayer() = apply {
    //     Allure.step("Navigate to player") {
    //         clickOnContentDescription("Player")
    //     }
    // }
}

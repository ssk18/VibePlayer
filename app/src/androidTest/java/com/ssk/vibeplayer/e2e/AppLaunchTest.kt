package com.ssk.vibeplayer.e2e

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ssk.vibeplayer.MainActivity
import com.ssk.vibeplayer.robots.MainScreenRobot
import com.ssk.vibeplayer.util.AllureTestRule
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Severity
import io.qameta.allure.kotlin.SeverityLevel
import io.qameta.allure.kotlin.Story
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * E2E test for app launch.
 *
 * Note: If you add Hilt to your app, you can enable @HiltAndroidTest
 * and add HiltAndroidRule for dependency injection in tests.
 */
// @HiltAndroidTest  // Uncomment when Hilt is configured
@Epic("VibePlayer App")
@Feature("App Launch")
class AppLaunchTest {

    // Uncomment when Hilt is configured:
    // @get:Rule(order = 0)
    // val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    val allureRule = AllureTestRule()

    private lateinit var mainScreen: MainScreenRobot

    @Before
    fun setup() {
        // hiltRule.inject()  // Uncomment when Hilt is configured
        mainScreen = MainScreenRobot(composeRule)
    }

    @Test
    @Story("App Launch")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that the app launches successfully and displays the main screen")
    fun appLaunchesSuccessfully() {
        mainScreen.assertMainScreenDisplayed()
    }
}

package com.ssk.vibeplayer.robots

import androidx.compose.ui.test.ComposeTimeoutException
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.qameta.allure.kotlin.Allure

/**
 * Base robot class providing common UI interaction methods.
 * Extend this class to create screen-specific robots.
 */
abstract class BaseRobot(protected val composeRule: ComposeTestRule) {

    protected fun clickOnText(text: String) = apply {
        Allure.step("Click on text: $text") {
            composeRule.onNodeWithText(text).performClick()
        }
    }

    protected fun clickOnContentDescription(description: String) = apply {
        Allure.step("Click on: $description") {
            composeRule.onNodeWithContentDescription(description).performClick()
        }
    }

    protected fun clickOnTag(tag: String) = apply {
        Allure.step("Click on element with tag: $tag") {
            composeRule.onNodeWithTag(tag).performClick()
        }
    }

    protected fun assertTextDisplayed(text: String) = apply {
        Allure.step("Assert text is displayed: $text") {
            composeRule.onNodeWithText(text).assertIsDisplayed()
        }
    }

    protected fun assertContentDescriptionDisplayed(description: String) = apply {
        Allure.step("Assert element is displayed: $description") {
            composeRule.onNodeWithContentDescription(description).assertIsDisplayed()
        }
    }

    protected fun assertTagDisplayed(tag: String) = apply {
        Allure.step("Assert element with tag is displayed: $tag") {
            composeRule.onNodeWithTag(tag).assertIsDisplayed()
        }
    }

    protected fun waitForIdle() = apply {
        composeRule.waitForIdle()
    }

    protected fun waitUntil(
        timeoutMillis: Long = 5000,
        condition: () -> Boolean
    ) = apply {
        composeRule.waitUntil(timeoutMillis) { condition() }
    }
}

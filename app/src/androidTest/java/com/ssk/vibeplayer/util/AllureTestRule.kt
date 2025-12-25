package com.ssk.vibeplayer.util

import android.graphics.Bitmap
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.io.ByteArrayOutputStream

/**
 * JUnit rule that captures a screenshot on test failure
 * and attaches it to the Allure report.
 */
class AllureTestRule : TestWatcher() {

    override fun failed(e: Throwable?, description: Description?) {
        try {
            val screenshot = InstrumentationRegistry.getInstrumentation()
                .uiAutomation.takeScreenshot()

            screenshot?.let { bitmap ->
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//                Allure.attachment(
//                    name = "Screenshot on failure",
//                    content = outputStream.toByteArray(),
//                    type = "image/png",
//                    fileExtension = ".png"
//                )
            }
        } catch (ex: Exception) {
            // Ignore screenshot capture failures
        }
    }
}

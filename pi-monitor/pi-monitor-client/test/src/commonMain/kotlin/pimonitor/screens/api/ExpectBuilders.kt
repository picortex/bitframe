package pimonitor.screens.api

import expect.BasicExpectation
import kotlin.test.assertTrue

inline fun <reified S : Screen> BasicExpectation<S>.toBeVisible() {
    assertTrue(
        """
        Expected ${S::class.simpleName} to be visible but was not
    """.trimIndent()
    ) { value.isVisible() }
}
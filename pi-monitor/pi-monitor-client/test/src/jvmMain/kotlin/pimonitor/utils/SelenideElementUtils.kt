package pimonitor.utils

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.SelenideElement
import expect.BasicExpectation
import expect.expect

fun SelenideElement.isVisible() = try {
    shouldBe(visible)
    true
} catch (err: Throwable) {
    false
}

fun BasicExpectation<SelenideElement>.toBeVisible() {
    expect(value.isVisible()).toBe(true)
}
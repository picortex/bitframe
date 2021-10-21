package pimonitor.utils

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors
import com.codeborne.selenide.SelenideElement
import expect.BasicExpectation
import expect.expect
import org.openqa.selenium.By
import kotlin.properties.ReadOnlyProperty
import com.codeborne.selenide.Selenide.`$` as S

fun SelenideElement.isVisible() = try {
    shouldBe(visible)
    true
} catch (err: Throwable) {
    false
}

fun BasicExpectation<SelenideElement>.toBeVisible() {
    expect(value.isVisible()).toBe(true)
}

fun name() = ReadOnlyProperty<Any?, SelenideElement> { thisRef, property ->
    S(By.name(property.name))
}

fun attr(key: String, value: String) = ReadOnlyProperty<Any?, SelenideElement> { thisRef, property ->
    S(Selectors.byAttribute(key, value))
}

fun submit() = attr("type", "submit")
package fields.text

import expect.expect
import live.expect
import live.toHaveGoneThrough3
import live.toHaveGoneThrough5
import presenters.fields.InputFieldState.Empty
import presenters.fields.InputFieldState.Warning
import presenters.fields.TextInputField
import kotlin.test.Test

class TextInputFeedbackTest {
    @Test
    fun should_warn_without_throwing_while_typing_input() {
        val field = TextInputField(name = "Test", minLength = 3, maxLength = 4)
        field.value = "A"
        field.value = "An"
        field.value = "And"
        field.value = "Ande"
        field.value = "Ander"
        field.value = "Anders"
        val (w1, w2, _, w4, w5) = expect(field.feedback).toHaveGoneThrough5<Warning, Warning, Empty, Warning, Warning>()
        listOf(w1, w2).forEach {
            expect(it.message).toBe("Test should not contain less than 3 characters")
        }

        listOf(w4, w5).forEach {
            expect(it.message).toBe("Test should not contain more than 4 characters")
        }
    }
}
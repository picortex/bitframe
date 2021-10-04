package acceptance

import expect.expect
import kotlin.test.Test

class ExhaustiveTest {
    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}
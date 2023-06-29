package acceptance

import expect.expect
import kommander.expect
import kotlin.test.Test

class ExhaustionTest {
    @Test
    fun should_pass() {
        expect<Int>(1 + 1).toBe(2)
    }
}
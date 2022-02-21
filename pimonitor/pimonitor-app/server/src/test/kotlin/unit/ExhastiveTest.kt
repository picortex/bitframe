package unit

import expect.expect
import kotlin.test.Test

class ExhastiveTest {
    @Test
    fun should_pass() {
        expect(1 + 2).toBe(2 + 1)
    }
}
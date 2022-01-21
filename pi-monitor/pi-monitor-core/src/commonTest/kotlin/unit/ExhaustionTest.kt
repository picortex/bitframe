package unit

import expect.expect
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ExhaustionTest {
    @Test
    fun should_pass() = runTest {
        expect(1 + 1).toBe(2)
    }
}
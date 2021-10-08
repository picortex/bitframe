package unit

import expect.expect
import kotlinx.coroutines.delay
import kotlinx.coroutines.runTest
import kotlin.test.Test

class ExhaustionTest {
    @Test
    fun should_pass() = runTest {
        expect(1 + 1).toBe(2)
    }
}
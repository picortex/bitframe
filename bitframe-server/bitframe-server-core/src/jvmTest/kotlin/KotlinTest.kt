import expect.expect
import expect.toBe
import kotlin.test.Test

class KotlinTest {

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}
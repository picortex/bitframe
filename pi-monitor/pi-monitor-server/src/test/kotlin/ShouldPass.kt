import kotlinx.coroutines.delay
import test.asyncTest
import kotlin.test.Test

class ShouldPass {

    @Test
    fun should_just_pass_for_fun() = asyncTest {
        delay(5000)
    }
}
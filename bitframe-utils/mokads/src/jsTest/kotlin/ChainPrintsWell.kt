import kollections.chainOf
import kotlin.test.Test

class ChainPrintsWell {
    @Test
    fun chain_should_print_internal_values() {
        val ints = chainOf(1, 2, 3, 4, 5, 6)
        println(ints)
    }
}
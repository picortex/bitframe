import ArrayInteroperabilityTest.Company
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlin.test.Test
import kotlin.test.assertEquals

class JsIteratorTest {
    val me = require<Me>("../../../../../bitframe-utils/kotlinx-collections-interoperable/src/jsTest/resources/me.js")
    val companies = listOf("google", "asoft", "yahoo").map { Company(it) }

    @Test
    fun should_run_a_file_in_resources() {
        println("\n\n")
        val stuff = me.doStuff(companies)
        println(stuff)
    }

    @Test
    fun can_convert_a_list_to_an_array() {
        println("\n\n")
        println(me.convertToArray(companies))
    }

    @Test
    fun can_call_unbounded() {
        val c = Calc(2)
        val res = me.addUnbounded(c, 2)
        assertEquals(4, res)
    }
}
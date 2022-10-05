import bitframe.core.filter
import bitframe.core.isEqualTo
import kotlin.test.Test

class QuerySyntaxTest {

    data class Person(
        val uid: String,
        val name: String
    )

    @Test
    fun can_write_a_simple_query() {
        filter(Person::uid isEqualTo "philip").limit(5).sortBy("uid")
    }
}
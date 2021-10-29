import expect.expect
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromDynamic
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ArrayInteropTest {
    data class Company(val name: String)

    @Test
    fun content_equals_should_return_true_when_contents_are_equal() {
        val list = listOf(Company("google"), Company("asoft"))
        val array = arrayOf(Company("google"), Company("asoft"))
        expect(list.contentDeepEquals(array)).toBe(true)
    }

    @Test
    fun content_equals_should_return_false_when_contents_are_not() {
        val list = listOf(Company("google"), Company("asoft"))
        val array = arrayOf(Company("google"), Company("apple"))
        expect(list.contentDeepEquals(array)).toBe(false)
    }

    @Test
    fun as_array_should_construct_a_new_array_when_possible() {
        val list = mutableListOf(Company("google"), Company("asoft"))
        val array1 = list.toArray()
        list[0] = Company("netflix")
        println(list)
        println(array1)
        println("\n\n")
        val array2 = list.toArray()
        println(list)
        println(array2)
        assertNotEquals(array2, array1)
    }
}
import expect.expect
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.mutableListOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ListTest {
    @Test
    fun should_be_instantiatable() {
        val list = listOf(1, 2, 3, 4, 5)
        println(list)
        expect(list).toContain(2, 3, 4)
    }

    @Test
    fun mutable_lists_should_be_like_lists() {
        val mutableList = mutableListOf(1, 2, 3, 4, 5, 6, 7)
        println(mutableList)
        expect(mutableList).toContain(2, 5, 7)
    }

    @Test
    fun empty_list_prints_correctly() {
        val emptyList = emptyList()
        println(emptyList)
        expect(emptyList.isEmpty()).toBe(true)
    }


    data class Company(val name: String)

    @Test
    fun as_array_should_not_construct_a_new_every_time() {
        val list = listOf(Company("google"), Company("asoft"))
        val array1 = list.asArray()
        val array2 = list.asArray()
        println(array1)
        println(array2)
        assertEquals(array1, array2)
        assertNotEquals(array2, list.toArray())
    }

    @Test
    fun as_array_should_construct_a_new_array_when_possible() {
        val list = mutableListOf(Company("google"), Company("asoft"))
        val array1 = list.asArray()
        list[0] = Company("netflix")
        val array2 = list.asArray()
        println(array1)
        println(array2)
        val array3 = list.asArray()
        assertNotEquals(array1, array2)
        assertNotEquals(array2, list.toArray())
        assertEquals(array2, array3)
    }
}
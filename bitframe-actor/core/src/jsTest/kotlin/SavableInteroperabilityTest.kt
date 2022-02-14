import expect.expect
import kotlin.test.Test

class SavableInteroperabilityTest {

    init {
        globalThis.person = Person(
            uid = "test-id",
            name = "John Doe",
            deleted = true
        )
    }

    val person get() = globalThis.person

    @Test
    fun should_get_id_from_savable() {
        val uid: String = person.uid
        expect(uid).toBe("test-id")
    }

    @Test
    fun should_get_deleted_from_savale() {
        val deleted: Boolean = person.deleted
        expect(deleted).toBe(true)
    }

    @Test
    fun should_get_name_from_savable() {
        val name: String = person.name
        expect(name).toBe("John Doe")
    }
}
package unit

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import expect.expect
import kotlin.test.Test

@JsExport
data class Person(
    val name: String = "John Doe",
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    companion object {
        val NAME = "John"
    }

    override fun copyId(uid: String) = copy(uid = uid)

    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}

external val globalThis: dynamic

class SavableInteroperabilityTest {

    @Test
    fun ensure_that_a_savable_is_interoperable() {
        globalThis.person = Person(
            uid = "test-id",
            name = "John Doe",
            deleted = true
        )
        console.log("\n\n", Person::class.js)
        console.log(globalThis.person)

        val name: String = globalThis.person.name
        expect(name).toBe("John Doe")

        val uid: String = globalThis.person.uid
        expect(uid).toBe("test-id")

        val deleted: Boolean = globalThis.person.deleted
        expect(deleted).toBe(true)
    }
}
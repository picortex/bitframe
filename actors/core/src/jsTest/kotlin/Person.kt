@file:JsExport

import bitframe.core.Savable

data class Person(
    val name: String = "John Doe",
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    companion object {
        val NAME = "John"
    }

    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}
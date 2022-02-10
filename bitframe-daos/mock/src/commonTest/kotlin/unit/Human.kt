package unit

import bitframe.actors.modal.HasId

data class Human(
    override val uid: String = "",
    val name: String
) : HasId {
    override fun copyId(uid: String): Human = copy(uid = uid)
}
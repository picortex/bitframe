package unit

import bitframe.modal.HasId

data class Human(
    override val uid: String = "",
    val name: String
) : HasId {
    override fun copy(id: String): Human = copy(uid = id)
}
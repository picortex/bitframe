import bitframe.actor.Savable
import bitframe.actor.UNSET
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val name: String,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}
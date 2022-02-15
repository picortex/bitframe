import bitframe.core.Savable
import kotlinx.serialization.Serializable

@Serializable
data class Human(
    val name: String,
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}
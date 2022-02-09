package bitframe.daos

import bitframe.actors.modal.HasId
import bitframe.actors.spaces.Space
import bitframe.actors.users.UserRef
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class History(
    val type: Type,
    val at: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val by: UserRef,
    val on: Space,
    override val uid: String
) : HasId {

    enum class Type {
        Created, Deleted, Updated
    }

    override fun copyId(id: String): History = copy(uid = id)
}
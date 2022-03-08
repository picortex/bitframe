package pimonitor.core.sage

import bitframe.core.Savable
import bitframe.core.UNSET

data class SageApiCredentials(
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}

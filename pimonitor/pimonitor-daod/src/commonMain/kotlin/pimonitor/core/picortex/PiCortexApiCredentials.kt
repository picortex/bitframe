package pimonitor.core.picortex

import bitframe.core.Savable
import bitframe.core.UNSET

data class PiCortexApiCredentials(
    val businessId: String,
    val subdomain: String,
    val secret: String,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}
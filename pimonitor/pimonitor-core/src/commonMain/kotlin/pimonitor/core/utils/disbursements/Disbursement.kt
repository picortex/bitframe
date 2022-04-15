@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursements

import akkounts.utils.unset
import bitframe.core.Savable
import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Disbursement(
    override val uid: String = unset,
    val amount: Money,
    val date: Date,
    val on: Date,
    val by: UserRef,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean): Savable = copy(uid = uid, deleted = deleted)
}
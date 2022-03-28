@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.investments

import akkounts.utils.unset
import bitframe.core.Savable
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Investment(
    override val uid: String = unset,
    val businessId: String,
    val name: String,
    val type: String,
    val source: String,
    val amount: Double,
    val date: Double,
    val details: String,
    val history: List<InvestmentHistory>,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}
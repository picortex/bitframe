@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import kotlin.js.JsExport

@Serializable(with = InfoResultsSerializer::class)
sealed class InfoResults<out T> {
    abstract val business: MonitoredBusinessBasicInfo

    @Serializable
    data class Shared<out T>(
        override val business: MonitoredBusinessBasicInfo,
        val data: T
    ) : InfoResults<T>()

    data class NotShared(
        override val business: MonitoredBusinessBasicInfo,
        val message: String
    ) : InfoResults<Nothing>()

    val areShared get() = this is Shared

    val asShared get() = this as Shared

    val areNotShared get() = this is NotShared

    val asNotShared get() = this as NotShared
}
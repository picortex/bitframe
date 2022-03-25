@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable(with = InfoResultsSerializer::class)
sealed class InfoResults<out T> {

    data class Shared<out T>(val data: T) : InfoResults<T>()

    data class NotShared(val message: String) : InfoResults<Nothing>()

    val areShared get() = this is Shared

    val asShared get() = this as Shared

    val areNotShared get() = this is NotShared

    val asNotShared get() = this as NotShared
}
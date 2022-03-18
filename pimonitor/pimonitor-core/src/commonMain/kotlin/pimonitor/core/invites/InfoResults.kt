@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.invites

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class InfoResults<out T> {
    data class Shared<out T>(val data: T) : InfoResults<T>()
    data class NotShared(val message: String) : InfoResults<Nothing>()
}
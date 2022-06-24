@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.actor

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Identified<out T>(
    override val uid: String,
    override val body: T
) : IdentifiedRaw<T> {
    fun <R> map(transform: (T) -> R) = Identified(uid, transform(body))
}
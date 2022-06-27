@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Deprecated("In favour of bitframe.actor.Identified")
@Serializable
class Identified<out T>(
    override val uid: String,
    override val body: T
) : IdentifiedRaw<T> {
    fun <R> map(transform: (T) -> R) = Identified(uid, transform(body))
}
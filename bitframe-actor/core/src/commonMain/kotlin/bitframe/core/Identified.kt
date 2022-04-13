@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Identified<out T : Any>(
    override val uid: String,
    override val body: T
) : IdentifiedRaw<T> {
    fun <R : Any> map(transform: (T) -> R) = Identified(uid, transform(body))
}
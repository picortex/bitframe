@file:JsExport

package bitframe.actor

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Identified<out I, out T>(
    override val uid: I,
    override val body: T
) : IdentifiedRaw<I, T> {
    fun <R> map(transform: (T) -> R) = Identified(uid, transform(body))
}
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlin.js.JsExport

@Deprecated("In favour of bitframe.actor.IdentifiedRaw")
@JsExport
interface IdentifiedRaw<out T> {
    val uid: String
    val body: T
}

@Deprecated("In favour of bitframe.actor.map")
inline fun <T, R> IdentifiedRaw<T>.map(transform: (T) -> R): IdentifiedRaw<R> = Identified(
    uid = uid,
    body = transform(body)
)

@Deprecated("In favour of bitframe.actor.toValidated")
fun <T> IdentifiedRaw<T>.toValidated() = Identified(
    uid = uid,
    body = body
)

@Deprecated("In favour of bitframe.actor.toValidated")
inline fun <T, S> IdentifiedRaw<T>.toValidated(transform: (T) -> S) = Identified(
    uid = uid,
    body = transform(body)
)
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.actor

import kotlin.js.JsExport

@JsExport
interface IdentifiedRaw<out T> {
    val uid: String
    val body: T
}

inline fun <T, R> IdentifiedRaw<T>.map(transform: (T) -> R): IdentifiedRaw<R> = Identified(
    uid = uid,
    body = transform(body)
)

fun <T> IdentifiedRaw<T>.toValidated() = Identified(
    uid = uid,
    body = body
)

inline fun <T, S> IdentifiedRaw<T>.toValidated(transform: (T) -> S) = Identified(
    uid = uid,
    body = transform(body)
)
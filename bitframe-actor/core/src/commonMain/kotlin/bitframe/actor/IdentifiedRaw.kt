@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.actor

import kotlin.js.JsExport

@JsExport
interface IdentifiedRaw<out I, out T> {
    val uid: I
    val body: T
}

inline fun <I, T, R> IdentifiedRaw<I, T>.map(transform: (T) -> R): IdentifiedRaw<I, R> = Identified(
    uid = uid,
    body = transform(body)
)

fun <I, T> IdentifiedRaw<I, T>.toValidated() = Identified(
    uid = uid,
    body = body
)

inline fun <I, T, S> IdentifiedRaw<I, T>.toValidated(transform: (T) -> S) = Identified(
    uid = uid,
    body = transform(body)
)
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlin.js.JsExport

@JsExport
interface IdentifiedRaw<out T : Any> {
    val uid: String
    val body: T
}

fun <T : Any> IdentifiedRaw<T>.toValidated() = Identified(
    uid = uid,
    body = body
)

inline fun <T : Any, S : Any> IdentifiedRaw<T>.toValidated(mapper: (T) -> S) = Identified(
    uid = uid,
    body = mapper(body)
)
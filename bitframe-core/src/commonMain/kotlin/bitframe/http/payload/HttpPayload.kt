@file:JsExport

package bitframe.http.payload

import kotlin.js.JsExport

sealed class HttpPayload<out D, out I>(open val data: D) {
    data class Informed<out D, out I>(
        override val data: D,
        val info: I,
    ) : HttpPayload<D, I>(data)

    data class Uniformed<D>(
        override val data: D
    ) : HttpPayload<D, Nothing>(data)

    companion object {
        fun <D> of(data: D): HttpPayload<D, Nothing> = Uniformed(data)

        fun <D, I : Any> of(data: D, info: I) = Informed(data, info)
    }

    val meta: I?
        get() = when (this) {
            is Informed -> info
            is Uniformed -> null
        }
}
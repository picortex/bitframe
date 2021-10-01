package bitframe.response

import kotlinx.serialization.KSerializer

data class Payload<D, I>(val data: D, val info: I) {
    internal var dataSerializer: KSerializer<D>? = null
    internal var infoSerializer: KSerializer<I>? = null

    companion object {
        fun <D> of(data: D): Payload<out D, out Nothing?> = Payload(data, null)
        fun <D, I> of(data: D, info: I): Payload<out D, out I> = Payload(data, info)
    }
}
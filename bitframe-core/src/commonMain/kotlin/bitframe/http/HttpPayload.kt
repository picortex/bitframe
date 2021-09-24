package bitframe.http

sealed class HttpPayload<out D, out I>(
    open val data: D,
    open val info: I
) {
    data class Informed<out D, out I>(
        override val data: D,
        override val info: I,
    ) : HttpPayload<D, I>(data, info)

    data class Uniformed<out D>(
        override val data: D
    ) : HttpPayload<D, Nothing?>(data, null)

    companion object {
        fun <D> of(data: D) = Uniformed(data)

        fun <D, I : Any> of(data: D, info: I) = Informed(data, info)
    }
}
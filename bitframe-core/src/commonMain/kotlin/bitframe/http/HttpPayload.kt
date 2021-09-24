package bitframe.http

data class HttpPayload<out D, out I>(val data: D, val info: I) {
    companion object {
        fun <D> of(data: D): HttpPayload<D, Nothing?> = HttpPayload(data, null)
        fun <D, I> of(data: D, info: I): HttpPayload<D, I> = HttpPayload(data, info)
    }
}
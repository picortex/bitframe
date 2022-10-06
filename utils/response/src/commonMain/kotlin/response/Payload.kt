package response

data class Payload<out D, out I>(val data: D, val info: I) {
    companion object {
        fun <D> of(data: D): Payload<D, Nothing?> = Payload(data, null)
        fun <D, I> of(data: D, info: I): Payload<D, I> = Payload(data, info)
    }
}
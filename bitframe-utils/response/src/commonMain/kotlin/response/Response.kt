package response

sealed class Response<out D, out I>(open val status: Status) {
    fun response(): D = when (val res = this) {
        is Failure -> throw ResponseException(res.error)
        is Success -> res.payload.data
    }

    fun responseWithInfo(): Pair<D, I> = when (val res = this) {
        is Failure -> throw ResponseException(res.error)
        is Success -> res.payload.data to res.payload.info
    }
}
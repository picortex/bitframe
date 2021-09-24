package bitframe.http.payload

fun <D> payloadOf(data: D): HttpPayload<D, Nothing> = HttpPayload.of(data)

fun <D, I : Any> payloadOf(data: D, info: I) = HttpPayload.of(data, info)
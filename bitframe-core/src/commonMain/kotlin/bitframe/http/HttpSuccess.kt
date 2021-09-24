package bitframe.http

import bitframe.http.payload.HttpPayload
import bitframe.http.payload.payloadOf
import io.ktor.http.*

data class HttpSuccess<out D, out I>(
    val status: HttpStatus,
    val payload: HttpPayload<D, I>
) : HttpResponse<D>() {
    constructor(data: D) : this(HttpStatusCode.OK, data)
    constructor(status: HttpStatus, data: D) : this(status, payloadOf(data))
    constructor(code: HttpStatusCode, data: D) : this(HttpStatus(code), data)
    constructor(code: HttpStatusCode, data: D, info: I) : this(HttpStatus(code), data, info)
    constructor(status: HttpStatus, data: D, info: I) : this(status, HttpPayload.Informed(data, info))
}
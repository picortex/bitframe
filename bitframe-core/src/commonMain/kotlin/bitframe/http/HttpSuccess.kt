package bitframe.http

data class HttpSuccess<out D>(
    val status: HttpStatus,
    val payload: HttpPayload<D>
) : HttpResponse<D>()
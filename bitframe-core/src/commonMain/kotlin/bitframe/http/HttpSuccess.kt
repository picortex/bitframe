package bitframe.http

data class HttpSuccess<out T>(
    val status: HttpStatus,
    val payload: HttpPayload<T>
) : HttpResponse<T>()
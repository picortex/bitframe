package bitframe.http

data class HttpFailure(
    override val status: HttpStatus,
    val error: HttpError
) : HttpResponse<Nothing, Nothing>(status)
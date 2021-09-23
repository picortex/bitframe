package bitframe.http

import kotlinx.serialization.Serializable

@Serializable
data class HttpFailure(
    val status: HttpStatus,
    val error: HttpError
) : HttpResponse<Nothing>()
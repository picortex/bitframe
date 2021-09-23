package bitframe.http

import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class HttpStatus(
    val code: Int,
    val message: String
) {
    constructor(code: HttpStatusCode) : this(code.value, code.description)
}
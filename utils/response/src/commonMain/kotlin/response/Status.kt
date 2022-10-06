package response

import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val code: Int,
    val message: String
) {
    constructor(code: HttpStatusCode) : this(code.value, code.description)
}
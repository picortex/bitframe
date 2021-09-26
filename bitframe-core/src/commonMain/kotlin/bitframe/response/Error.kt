package bitframe.response

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads

@Serializable
data class Error(
    val message: String,
    val type: String,
    val cause: String
) {
    @JvmOverloads
    constructor(error: Throwable, message: String? = null) : this(
        message = message ?: error.message ?: "Unknown",
        type = error::class.qualifiedName ?: "Unknown",
        cause = (if (message == null) error.cause?.message else error.message) ?: "Unknown"
    )
}
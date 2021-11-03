package validation

open class ValidationError(message: String?, cause: Throwable?) : IllegalArgumentException(message, cause)
package validation

inline fun <T> validate(validator: () -> T): Validation<T> = try {
    Valid(validator())
} catch (cause: Throwable) {
    Invalid(ValidationException(cause.message, cause))
}
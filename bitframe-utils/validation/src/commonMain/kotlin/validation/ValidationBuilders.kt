package validation

inline fun <T> validate(validator: () -> T): Validation<T> = try {
    Valid(validator())
} catch (cause: Throwable) {
    Invalid(ValidationError(cause.message, cause))
}
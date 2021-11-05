package validation

inline fun <T> validate(validator: () -> T): Validation<T> = try {
    Validation.Valid(validator())
} catch (cause: Throwable) {
    Validation.Invalid(ValidationError(cause.message, cause))
}
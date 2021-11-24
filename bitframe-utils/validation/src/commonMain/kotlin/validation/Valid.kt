package validation

data class Valid<out T>(val value: T) : Validation<T>()
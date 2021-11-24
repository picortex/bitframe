package validation

data class Invalid(val cause: ValidationError) : Validation<Nothing>()
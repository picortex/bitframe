package bitframe.core

@Deprecated("In favour of bitframe.exceptions.InvalidIdentifier")
class InvalidIdentifierException(val value: String) : IllegalArgumentException(
    "Identifier with value $value is neither a valid email or a valid phone number"
)
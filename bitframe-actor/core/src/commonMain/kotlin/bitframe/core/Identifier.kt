package bitframe.core

import identifier.Email as PrimitiveEmail
import identifier.Phone as PrimitivePhone

@Deprecated("In favour of bitframe.Identifier")
sealed class Identifier(open val primitive: Any, val value: String) {
    companion object {
        fun fromOrNull(value: String): Identifier? = try {
            Email(PrimitiveEmail(value))
        } catch (cause: Throwable) {
            null
        } ?: try {
            Phone(PrimitivePhone(value))
        } catch (cause: Throwable) {
            null
        }

        fun from(value: String): Identifier = fromOrNull(value) ?: throw InvalidIdentifierException(value)
    }

    data class Phone(
        override val primitive: PrimitivePhone
    ) : Identifier(primitive, primitive.value)

    data class Email(
        override val primitive: PrimitiveEmail
    ) : Identifier(primitive, primitive.value)
}
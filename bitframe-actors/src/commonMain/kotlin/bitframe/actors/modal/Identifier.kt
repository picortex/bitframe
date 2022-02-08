package bitframe.actors.modal

import identifier.Email as PrimitiveEmail
import identifier.Phone as PrimitivePhone

sealed class Identifier(open val primitive: Any, val value: String) {
    companion object {
        fun from(value: String): Identifier = try {
            Email(PrimitiveEmail(value))
        } catch (cause: Throwable) {
            null
        } ?: try {
            Phone(PrimitivePhone(value))
        } catch (cause: Throwable) {
            null
        } ?: throw InvalidIdentifierException(value)
    }

    data class Phone(
        override val primitive: PrimitivePhone
    ) : Identifier(primitive, primitive.value)

    data class Email(
        override val primitive: PrimitiveEmail
    ) : Identifier(primitive, primitive.value)
}
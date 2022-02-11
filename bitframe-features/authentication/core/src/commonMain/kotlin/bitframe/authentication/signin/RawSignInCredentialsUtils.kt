package bitframe.authentication.signin

import bitframe.actors.modal.Identifier
import validation.required
import validation.validate

fun IRawSignInCredentials.toSignInCredentials() = validate {
    RawSignInCredentials(
        identifier = Identifier.from(
            email ?: phone ?: identifier ?: throw IllegalArgumentException("missing identifier, email|phone")
        ).value,
        password = required(::password)
    )
}.getOrThrow()
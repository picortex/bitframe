package bitframe.core.signin

import bitframe.core.Identifier
import validation.required
import validation.validate

fun RawSignInCredentials.toSignInCredentials() = validate {
    SignInCredentials(
        identifier = Identifier.from(
            email ?: phone ?: identifier ?: throw IllegalArgumentException("missing identifier, email|phone")
        ).value,
        password = required(::password)
    )
}.getOrThrow()
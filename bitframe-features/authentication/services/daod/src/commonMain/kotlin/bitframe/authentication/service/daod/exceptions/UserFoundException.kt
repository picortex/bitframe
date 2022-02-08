package bitframe.authentication.service.daod.exceptions

class UserFoundException(
    val identifier: String
) : RuntimeException(
    "User (identifier = $identifier) already exists"
)
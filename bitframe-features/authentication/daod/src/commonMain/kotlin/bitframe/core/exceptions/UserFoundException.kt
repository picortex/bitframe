package bitframe.core.exceptions

class UserFoundException(
    val identifier: String
) : RuntimeException(
    "User (identifier = $identifier) already exists"
)
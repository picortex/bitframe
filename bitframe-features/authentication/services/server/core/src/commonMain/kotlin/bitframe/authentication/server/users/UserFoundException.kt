package bitframe.authentication.server.users

class UserFoundException(
    val identifier: String
) : RuntimeException(
    "User (identifier = $identifier) already exists"
)
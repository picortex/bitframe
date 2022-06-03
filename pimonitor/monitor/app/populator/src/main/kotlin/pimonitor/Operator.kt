package pimonitor

data class Operator(
    val name: String,
    val email: String,
    val password: String = email
)
package bitframe.core.rest

sealed class AuthEndpoint(private val root: String) {
    class Client(url: String, version: String) : AuthEndpoint("$url/api/$version")
    class Server(version: String) : AuthEndpoint("/api/$version")

    val signIn = "$root/sign-in"
    val changePassword = "$root/change-password"
}
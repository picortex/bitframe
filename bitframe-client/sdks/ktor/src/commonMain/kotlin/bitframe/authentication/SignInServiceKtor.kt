package bitframe.authentication

import bitframe.MiniService
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.response.response.decodeResponseFromString
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import kotlin.jvm.JvmOverloads

internal class SignInServiceKtor @JvmOverloads constructor(
    private val configuration: KtorClientConfiguration
) : SignInService(), MiniService {
    private val path = configuration.url + "/api/authentication/sign-in"
    override val config: ClientConfiguration = configuration
    private val http = configuration.http
    override fun signIn(credentials: SignInCredentials): Later<LoginConundrum> = configuration.scope.later {
        val json = http.post<String>(path) {
            body = TextContent(
                text = Json.encodeToString(SignInCredentials.serializer(), credentials),
                contentType = ContentType.Application.Json
            )
        }
        Json.decodeResponseFromString(LoginConundrum.serializer(), json).response()
    }
}
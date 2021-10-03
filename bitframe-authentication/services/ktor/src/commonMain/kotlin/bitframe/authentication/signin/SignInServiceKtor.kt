package bitframe.authentication.signin

import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import kotlin.jvm.JvmOverloads

class SignInServiceKtor @JvmOverloads constructor(
    override val config: KtorClientConfiguration
) : SignInService(), MiniService {
    private val path = config.url + "/api/authentication/sign-in"
    private val http = config.http
    private val scope = config.scope
    override fun signIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        validate(credentials)
        val json = http.post<String>(path) {
            body = TextContent(
                text = Json.encodeToString(SignInCredentials.serializer(), credentials),
                contentType = ContentType.Application.Json
            )
        }
        Json.decodeResponseFromString(LoginConundrum.serializer(), json).response()
    }
}
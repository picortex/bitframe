package bitframe.authentication

import duality.Result
import duality.parse
import duality.response
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import later.Later
import later.later
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

internal class SignInServiceKtor @JvmOverloads constructor(
    private val configuration: KtorClientConfiguration
) : SignInService {
    private val path = configuration.url + "/api/authentication/sign-in"
    override val config: ClientConfiguration = configuration
    private val http = configuration.http
    override fun signIn(credentials: LoginCredentials): Later<LoginConundrum> = configuration.scope.later {
        val json = http.post<String>(path) {
            body = TextContent(
                text = Json.encodeToString(LoginCredentials.serializer(), credentials),
                contentType = ContentType.Application.Json
            )
        }
        Result.parse(LoginConundrum.serializer(), json).response()
    }
}
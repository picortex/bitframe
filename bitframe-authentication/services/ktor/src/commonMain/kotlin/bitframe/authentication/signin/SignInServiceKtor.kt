package bitframe.authentication.signin

import bitframe.events.EventBus
import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.utils.JsonContent
import cache.Cache
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import later.Later
import later.later

open class SignInServiceKtor(
    override val config: KtorClientConfiguration,
    override val cache: Cache,
    override val bus: EventBus
) : SignInService(bus, cache,config), MiniService {
    private val path get() = config.url + "/api/authentication/sign-in"
    private val http get() = config.http
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val resp = try {
            http.post(path) { body = JsonContent(credentials) }
        } catch (exp: ClientRequestException) {
            exp.response
        }
        Json.decodeResponseFromString(LoginConundrum.serializer(), resp.readText()).response()
    }
}
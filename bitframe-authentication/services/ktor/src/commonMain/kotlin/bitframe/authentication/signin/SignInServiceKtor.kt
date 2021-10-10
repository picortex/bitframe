package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.response.response.decodeResponseFromString
import bitframe.service.MiniService
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.utils.JsonContent
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
            body = JsonContent(credentials, SignInCredentials.serializer())
        }
        Json.decodeResponseFromString(LoginConundrum.serializer(), json).response()
    }

    override fun resolve(space: Space): Later<Session.SignedIn> = scope.later {
        val error = IllegalStateException(
            """
                You are attempting to resolve a non exiting conundrum,
                
                Make sure you have tried to signIn and the result obtained was a LoginConundrum with more that one space
                """.trimIndent()
        )
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.Conundrum -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.SignedOut -> throw error
        }.also { session.value = it }
    }
}
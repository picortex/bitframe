package bitframe.authentication

import io.ktor.client.request.*
import later.Later
import later.later
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

class KtorSignInService @JvmOverloads constructor(
    private val configuration: KtorClientConfiguration
) : SignInService {
    private val path = configuration.url + "/api/authentication/sign-in"
    override val config: ClientConfiguration = configuration
    override fun loginWith(credentials: LoginCredentials): Later<LoginConundrum> = configuration.scope.later {
        val json = configuration.http.post<String>(path)
        TODO()
    }
}
package bitframe.authentication

import io.ktor.client.request.*
import later.Later
import later.later
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@JsExport
class KtorLoginService @JvmOverloads constructor(
    val configuration: KtorClientConfiguration
) : LoginService {
    override val config: ClientConfiguration = configuration
    override fun loginWith(credentials: LoginCredentials): Later<LoginConundrum> = configuration.scope.later {
        val json = configuration.http.get<String>(configuration.url)
        TODO()
    }
}
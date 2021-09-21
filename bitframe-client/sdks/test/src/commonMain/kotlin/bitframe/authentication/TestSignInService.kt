package bitframe.authentication

import bitframe.BitframeTestClient
import kotlinx.coroutines.delay
import later.later
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@JsExport
class TestSignInService @JvmOverloads constructor(
    val configuration: TestClientConfiguration = BitframeTestClient.CONFIGURATION,
    val db: UserDatabase = UserDatabase()
) : SignInService {
    override val config: ClientConfiguration = configuration
    override fun loginWith(credentials: LoginCredentials) = configuration.scope.later {
        delay(configuration.simulationTime.toLong())
        val cred = db.credentialsFor(credentials.alias)
        if (credentials.password == cred?.password) {
            val accounts = db.accountsFor(credentials.alias)
            LoginConundrum(User(credentials.alias), accounts)
        } else {
            throw Exception("Invalid Login Credentials")
        }
    }
}
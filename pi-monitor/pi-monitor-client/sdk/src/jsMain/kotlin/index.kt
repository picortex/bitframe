import bitframe.BitframeService
import bitframe.BitframeTestClient
import bitframe.authentication.LoginCredentials
import bitframe.authentication.LoginViewModel
import bitframe.authentication.TestClientConfiguration

external interface Configuration {
    val appId: String
}

@JsExport
@JsName("service")
private fun PiMonitorService(config: Configuration) = BitframeTestClient(
    TestClientConfiguration(appId = config.appId)
)

@JsExport
fun loginViewModel(service: BitframeService) = LoginViewModel(service.authentication)

external interface Credentials {
    val username: String
    val password: String
}

@JsExport
fun loginIntent(
    credentials: Credentials
) = LoginViewModel.Intent.Login(
    LoginCredentials(
        username = credentials.username,
        password = credentials.password
    )
)
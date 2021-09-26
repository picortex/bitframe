package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestSignInService
import bitframe.authentication.signin.SignInService
import kotlin.js.JsExport

@JsExport
open class BitframeTestClientImpl(override val configuration: TestClientConfiguration) : BitframeTestClient {
    override val config: ClientConfiguration = configuration
    override val signIn: SignInService = TestSignInService(configuration)
}
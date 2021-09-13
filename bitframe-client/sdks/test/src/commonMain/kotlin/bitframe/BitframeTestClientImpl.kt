package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestSignInService
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@JsExport
open class BitframeTestClientImpl(override val configuration: TestClientConfiguration) : BitframeTestClient {
    override val config: ClientConfiguration = configuration
    override val signIn: SignInService = TestSignInService(configuration)
}
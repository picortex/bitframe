@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration

external interface Configuration {
    var appId: String
}

@JsExport
fun service(config: Configuration) = BitframeTestClient(
    TestClientConfiguration(appId = config.appId)
)
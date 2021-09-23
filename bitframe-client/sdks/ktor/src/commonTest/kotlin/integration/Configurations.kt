package integration

import bitframe.authentication.KtorClientConfiguration
import kotlin.jvm.JvmField

private val DEVELOPMENT_CONFIGURATION = KtorClientConfiguration(
    url = "http://localhost:8080",
    appId = "1234"
)

@JvmField
val CONFIGURATION_UNDER_TEST = DEVELOPMENT_CONFIGURATION
package integration.ktor.utils

import bitframe.authentication.KtorClientConfiguration
import kotlin.jvm.JvmField

private val DEV_CONFIGURATION = KtorClientConfiguration(
    url = "http://localhost:8080",
    appId = "dev-1234"
)

@JvmField
internal val CONFIGURATION_UNDER_TEST = DEV_CONFIGURATION
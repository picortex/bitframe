package unit.utils

import bitframe.authentication.KtorClientConfiguration

private val DEV_CONFIGURATION = KtorClientConfiguration(
    url = "http://localhost:8080",
    appId = "app-1"
)

val CONFIGURATION_UNDER_TEST = DEV_CONFIGURATION
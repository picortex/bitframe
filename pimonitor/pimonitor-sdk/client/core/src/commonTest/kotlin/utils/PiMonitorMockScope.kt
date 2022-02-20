package utils

import API_MODE
import API_URL
import bitframe.client.BitframeAppScopeConfig
import bitframe.client.BitframeViewModelConfig
import pimonitor.client.*
import validation.required
import validation.requiredNotBlank

fun PiMonitorTestApi(): PiMonitorApi = api {
    namespace = "pimonitor.testing"
    appId = "<integration-test-app>"
    url = when (API_MODE) {
        "MOCK" -> ""
        "LIVE" -> requiredNotBlank(::API_URL)
        else -> error("Unknown test mode: $API_MODE")
    }
}

fun PiMonitorMockScope() = PiMonitorAppScope(
    BitframeAppScopeConfig(
        api = PiMonitorTestApi(),
        viewModel = BitframeViewModelConfig(
            recoveryTime = 0L
        )
    )
)
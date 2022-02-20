package pimonitor.client

import validation.requiredNotBlank

fun PiMonitorApiTest(): PiMonitorApi = api {
    namespace = "pimonitor.testing"
    appId = "<integration-test-app>"
    url = when (API_MODE) {
        "MOCK" -> ""
        "LIVE" -> requiredNotBlank(::API_URL)
        else -> error("Unknown test mode: $API_MODE")
    }
}
package pimonitor.client

import validation.requiredNotBlank

fun MonitorApiTest(): MonitorApi = api {
    namespace = "pimonitor.testing"
    appId = "<integration-test-app>"
    url = when (API_MODE.uppercase()) {
        "MOCK" -> ""
        "LIVE" -> requiredNotBlank(::API_URL)
        else -> error("Unknown test mode: $API_MODE")
    }
}.also {
    println("Using $it")
}
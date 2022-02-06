package pimonitor.client.monitors

import bitframe.authentication.users.UserRef
import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.utils.JsonContent
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import later.Later
import later.later
import pimonitor.monitors.Monitor

class MonitorsServiceKtor(
    override val config: MonitorsServiceKtorConfig
) : MonitorsService(config) {
    init {
        watchSignInSession()
    }

    val url = config.url + "/api/monitors"
    val http = config.http
    val json = config.json

    override fun load(uid: String): Later<Monitor?> = scope.later {
        val resp = http.get("$url/$uid")
        json.decodeResponseFromString(Monitor.serializer(), resp.bodyAsText()).response()
    }

    @OptIn(InternalAPI::class)
    override fun monitor(with: UserRef): Later<Monitor> = scope.later {
        val resp = http.post("$url/user") { body = JsonContent(with) }
        json.decodeResponseFromString(Monitor.serializer(), resp.bodyAsText()).response()
    }
}
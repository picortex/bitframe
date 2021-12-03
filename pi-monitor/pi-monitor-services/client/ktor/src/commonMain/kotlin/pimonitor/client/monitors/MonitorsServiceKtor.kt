package pimonitor.client.monitors

import bitframe.authentication.users.UserRef
import bitframe.response.response.decodeResponseFromString
import bitframe.service.client.utils.JsonContent
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.client.monitors.MonitorsService
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
        val resp = try {
            http.get("$url/$uid")
        } catch (exp: ClientRequestException) {
            exp.printStackTrace()
            exp.response
        }
        json.decodeResponseFromString(Monitor.serializer(), resp.readText()).response()
    }

    override fun monitor(with: UserRef): Later<Monitor> = scope.later {
        val resp = try {
            http.post("$url/user") { body = JsonContent(with) }
        } catch (cause: ClientRequestException) {
            cause.response
        }
        json.decodeResponseFromString(Monitor.serializer(), resp.readText()).response()
    }
}
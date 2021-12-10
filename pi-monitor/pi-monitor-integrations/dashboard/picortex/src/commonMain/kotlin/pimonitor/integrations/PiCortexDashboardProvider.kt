package pimonitor.integrations

import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.mapper.Mapper
import later.later

class PiCortexDashboardProvider(
    val config: PiCortexDashboardProviderConfig = PiCortexDashboardProviderConfig()
) : DashboardProvider {

    private val scope get() = config.scope
    private val client get() = config.client
    private val parser get() = config.parser

    fun technicalDashboardOf(credentials: PiCortexUserCredentials) = scope.later<TechnicalDashboard> {
        val res = try {
            val map = mapOf(
                "secret" to credentials.secret,
                "userType" to "DataConsoleUser"
            )
            client.post("https://${credentials.subdomain}.picortex.com/api/reporting") {
                body = TextContent(
                    text = Mapper.encodeToString(map),
                    contentType = ContentType.Application.Json
                )
            }
        } catch (exp: ResponseException) {
            exp.response
        }
        parser.parseTechnicalDashboard(res.readText())
    }
}
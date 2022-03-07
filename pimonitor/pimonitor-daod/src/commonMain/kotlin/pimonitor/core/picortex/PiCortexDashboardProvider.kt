package pimonitor.core.picortex

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

    fun technicalDashboardOf(credentials: PiCortexApiCredentials) = scope.later<OperationalDashboard> {
        val map = mapOf(
            "secret" to credentials.secret,
            "userType" to "DataConsoleUser"
        )
        val res = client.post("https://${credentials.subdomain}.picortex.com/api/reporting") {
            setBody(
                TextContent(
                    text = Mapper.encodeToString(map),
                    contentType = ContentType.Application.Json
                )
            )
        }
        parser.parseTechnicalDashboard(res.bodyAsText())
    }
}
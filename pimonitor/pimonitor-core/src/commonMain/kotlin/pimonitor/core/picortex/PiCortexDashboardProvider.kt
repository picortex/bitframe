package pimonitor.core.picortex

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.serialization.mapper.Mapper
import later.later
import pimonitor.core.dashboards.DashboardProvider
import pimonitor.core.dashboards.OperationalDashboard

class PiCortexDashboardProvider(
    val config: PiCortexDashboardProviderConfig = PiCortexDashboardProviderConfig()
) : DashboardProvider {

    private val scope get() = config.scope
    private val client get() = config.client
    private val parser get() = config.parser
    private val domain get() = config.environment.domain

    fun technicalDashboardOf(credentials: PiCortexApiCredentials) = scope.later<OperationalDashboard> {
        val params = mapOf(
            "secret" to credentials.secret,
            "userType" to "DataConsoleUser"
        )
        val url = "https://${credentials.subdomain}.$domain/api/reporting"
        println(credentials)
        println(url)
        println(params)
        val res = client.post(url) {
            setBody(
                TextContent(
                    text = Mapper.encodeToString(params),
                    contentType = ContentType.Application.Json
                )
            )
        }
        println(res.bodyAsText())
        parser.parseTechnicalDashboard(res.bodyAsText())
    }
}
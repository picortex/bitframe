package integration

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.runTest
import kotlinx.serialization.mapper.Mapper
import kotlin.test.Test

class PiCortexDashboardTest {

    @Test
    fun should_be_able_to_fetch() = runTest {
        val client = HttpClient {}
        val map = mapOf(
            "secret" to "89aqiclvjktp0aa4bgfqpbppf6",
            "userType" to "DataConsoleUser"
        )
        val resp = try {
            client.post("https://b2bdemo.picortex.com/api/reporting") {
                body = TextContent(
                    text = Mapper.encodeToString(map),
                    contentType = ContentType.Application.Json
                )
            }
        } catch (exp: ClientRequestException) {
            exp.response
        }
        println(resp.readText())
    }
}
package pimonitor.client

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class RestTestDecider(val url: String) : TestDecider {
    val http = HttpClient {
        expectSuccess = false
    }
    private var decision: TestType? = null
    override suspend fun decide(): TestType = try {
        if (decision != null) {
            decision!!
        } else {
            http.get(url).bodyAsText()
            TestType.INTEGRATION
        }
    } catch (err: Throwable) {
        TestType.UNIT
    }.also { decision = it }
}
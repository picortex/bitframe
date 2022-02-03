package pimonitor.integrations

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmOverloads

class PiCortexDashboardProviderConfig @JvmOverloads constructor(
    val json: Json = DEFAULT_JSON,
    val client: HttpClient = DEFAULT_HTTP_CLIENT,
    val scope: CoroutineScope = DEFAULT_SCOPE,
) {
    internal val parser by lazy { PiCortexDashboardParser(json) }

    companion object {
        val DEFAULT_JSON = Json { }
        val DEFAULT_HTTP_CLIENT = HttpClient { }
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
    }
}
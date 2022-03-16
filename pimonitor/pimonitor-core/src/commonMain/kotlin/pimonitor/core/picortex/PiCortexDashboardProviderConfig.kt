package pimonitor.core.picortex

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmOverloads

class PiCortexDashboardProviderConfig @JvmOverloads constructor(
    val json: Json = DEFAULT_JSON,
    val client: HttpClient = DEFAULT_HTTP_CLIENT,
    val scope: CoroutineScope = DEFAULT_SCOPE,
    val environment: Environment = Environment.Production
) {
    internal val parser by lazy { PiCortexDashboardParser(json) }

    enum class Environment(val domain: String) {
        Staging("picortex.co"),
        Production("picortex.com")
    }

    companion object {
        val DEFAULT_JSON: Json = Json { }
        val DEFAULT_HTTP_CLIENT = HttpClient { }
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
    }
}
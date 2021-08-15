package bitframe.authentication

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

class KtorClientConfiguration @JvmOverloads constructor(
    val url: String,
    override val appId: String,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    val http: HttpClient = HttpClient { },
) : ClientConfiguration(appId, scope)
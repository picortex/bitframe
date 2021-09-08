package bitframe.authentication

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

class KtorClientConfiguration @JvmOverloads constructor(
    val url: String,
    override val appId: String,
    val http: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : ClientConfiguration(appId, scope)
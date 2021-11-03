package bitframe.authentication.client.signin

import bitframe.events.EventBus
import bitframe.service.client.config.KtorClientConfiguration
import cache.Cache
import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SignInServiceKtorConfig(
    override val appId: String,
    override val url: String,
    override val cache: Cache,
    override val bus: EventBus,
    override val http: HttpClient = HttpClient { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : SignInServiceConfig, KtorClientConfiguration
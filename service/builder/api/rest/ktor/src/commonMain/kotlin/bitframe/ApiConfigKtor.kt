package bitframe

import io.ktor.client.HttpClient
import koncurrent.CoroutineExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField

interface ApiConfigKtor<out E> : ApiConfig, ApiConfigRest<E, HttpClient> {
    override val http: HttpClient
    override val executor: CoroutineExecutor
    val scope: CoroutineScope get() = executor.scope

    companion object {
        @JvmField
        val DEFAULT_HTTP_CLIENT = HttpClient {
            expectSuccess = false
        }

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(Dispatchers.Default + SupervisorJob())

        @JvmField
        val DEFAULT_EXECUTOR = CoroutineExecutor(DEFAULT_SCOPE)
    }
}
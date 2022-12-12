package bitframe

import io.ktor.client.*
import koncurrent.CoroutineExecutor
import kotlinx.coroutines.CoroutineScope

interface ApiConfigRestKtor<out E> : ApiConfigRest<E, HttpClient> {
    override val executor: CoroutineExecutor
    val scope: CoroutineScope get() = executor.scope

    override fun <E2> map(transform: (E) -> E2): ApiConfigRestKtor<E2>
}
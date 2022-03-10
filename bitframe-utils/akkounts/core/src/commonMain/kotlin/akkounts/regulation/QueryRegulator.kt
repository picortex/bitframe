package akkounts.regulation

import akkounts.provider.Vendor
import akkounts.provider.Owner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.*
import later.Later
import later.await
import later.later
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.time.ExperimentalTime

class QueryRegulator @JvmOverloads constructor(
    private val policy: Policy,
    private val store: QueryCountStore = InMemoryQueryCountStore(),
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) {
    companion object {
        @JvmField
        val FORGIVING = QueryRegulator(Policy.LOOSE, InMemoryQueryCountStore())
    }

    enum class Status {
        Allowed, Blocked
    }

    @OptIn(ExperimentalTime::class)
    fun checkRestriction(requester: Owner, vendor: Vendor): Later<Status> = scope.later {
        val nowInstant = Clock.System.now()
        val count = store.load(requester, vendor).await() ?: QueryCount.initial(requester, vendor)
        val dt = nowInstant - count.lastRequestedOn.toInstant(TimeZone.UTC)
        when {
            dt < policy.maxPeriod.duration && count.requestCount > policy.maxRequests -> Status.Blocked
            else -> Status.Allowed
        }
    }

    @OptIn(ExperimentalTime::class)
    fun incrementCounter(requester: Owner, vendor: Vendor) = scope.later {
        val now = Clock.System.now()
        val count = store.load(requester, vendor).await() ?: QueryCount.initial(requester, vendor)
        val dt = now - count.lastRequestedOn.toInstant(TimeZone.UTC)
        val newCount = if (dt > policy.maxPeriod.duration) count.copy(
            lastRequestedOn = now.toLocalDateTime(TimeZone.UTC),
            requestCount = 1
        ) else count.copy(
            requestCount = count.requestCount + 1
        )
        store.save(newCount).await()
    }
}
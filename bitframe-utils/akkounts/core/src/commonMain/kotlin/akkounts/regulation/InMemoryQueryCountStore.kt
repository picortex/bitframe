package akkounts.regulation;

import akkounts.provider.Vendor
import akkounts.provider.Owner
import later.Later
import kotlin.jvm.JvmOverloads

class InMemoryQueryCountStore @JvmOverloads constructor(
    private val map: MutableMap<Vendor, MutableMap<Owner, QueryCount>> = mutableMapOf()
) : QueryCountStore {
    override fun save(count: QueryCount): Later<QueryCount> = Later { res, _ ->
        val ownerCountMap = map.getOrPut(count.vendor) { mutableMapOf() }
        ownerCountMap[count.requester] = count
        res(count)
    }

    override fun load(requester: Owner, vendor: Vendor): Later<QueryCount?> = Later { res, _ ->
        res(map[vendor]?.get(requester))
    }
}

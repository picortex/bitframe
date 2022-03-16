package akkounts.regulation

import akkounts.provider.Vendor
import akkounts.provider.Owner
import later.Later

interface QueryCountStore {
    fun save(count: QueryCount): Later<QueryCount>
    fun load(requester: Owner, vendor: Vendor): Later<QueryCount?>
}
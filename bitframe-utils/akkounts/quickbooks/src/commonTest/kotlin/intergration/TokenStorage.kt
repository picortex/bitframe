package intergration

import akkounts.QuickBooksCompany
import akkounts.QuickBooksTokenStorage
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import later.Later

class TokenStorage : QuickBooksTokenStorage {
    private val companies: AtomicRef<Map<String, QuickBooksCompany>> = atomic(mutableMapOf())

    @OptIn(ExperimentalStdlibApi::class)
    override fun updateOrCreate(company: QuickBooksCompany): Later<QuickBooksCompany> = Later { res, _ ->
        val map = buildMap<String, QuickBooksCompany> {
            putAll(companies.value)
            put(company.uid, company)
        }
        companies.lazySet(map)
        res(company)
    }

    override fun load(uid: String): Later<QuickBooksCompany?> = Later { res, _ ->
        res(companies.value[uid])
    }
}
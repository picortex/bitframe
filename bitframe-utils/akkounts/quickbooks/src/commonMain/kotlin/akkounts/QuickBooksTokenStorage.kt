package akkounts

import later.Later

interface QuickBooksTokenStorage {
    fun updateOrCreate(company: QuickBooksCompany): Later<QuickBooksCompany>
    fun load(uid: String): Later<QuickBooksCompany?>
}
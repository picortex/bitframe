package akkounts.accounts

import later.Later

interface AccountsService {
    fun create(params: AccountParams): Later<Account>
    fun getOrCreate(params: AccountParams): Later<Account>
    fun search(name: String): Later<List<Account>>
}
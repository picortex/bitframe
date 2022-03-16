package akkounts.accounts

import akkounts.provider.Vendor
import akkounts.unimplemented.utils.Unimplemented
import later.Later

class UnimplementedAccountsService(val vendor: Vendor) : AccountsService {
    val feature = "Accounts"
    override fun create(params: AccountParams): Later<Account> {
        Unimplemented(vendor, feature)
    }

    override fun getOrCreate(params: AccountParams): Later<Account> {
        Unimplemented(vendor, feature)
    }

    override fun search(name: String): Later<List<Account>> {
        Unimplemented(vendor, feature)
    }
}
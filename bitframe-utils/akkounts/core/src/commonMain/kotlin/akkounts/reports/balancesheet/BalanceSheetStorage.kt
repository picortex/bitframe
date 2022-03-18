package akkounts.reports.balancesheet

import akkounts.provider.Vendor
import akkounts.provider.Owner
import kotlinx.datetime.LocalDate
import later.Later

interface BalanceSheetStorage {
    fun save(owner: Owner, vendor: Vendor, header: BalanceSheet.Header, body: BalanceSheet.Body): Later<BalanceSheet>

    @Deprecated("use load(ownerId, endOf: LocalDate)")
    fun load(ownerId: String, endOf: Long): Later<BalanceSheet?>

    fun load(owner: Owner, vendor: Vendor, at: LocalDate): Later<BalanceSheet?>
}
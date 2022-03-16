package akkounts.reports.incomestatement

import akkounts.provider.Vendor
import akkounts.provider.Owner
import kotlinx.datetime.LocalDate
import later.Later

interface IncomeStatementStorage {
    fun save(owner: Owner, vendor: Vendor, data: IncomeStatement): Later<IncomeStatement>

    @Deprecated("Use load")
    fun load(ownerId: String, endOf: Long): Later<IncomeStatement?>

    fun load(ownerId: String, start: LocalDate, end: LocalDate): Later<IncomeStatement>
}
package akkounts.sage

import akkounts.provider.AccountingPackager
import akkounts.provider.Vendor
import akkounts.regulation.QueryRegulator
import akkounts.provider.Owner
import akkounts.sage.reports.SageOneZAReportsService
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

class SageOneZAService @JvmOverloads constructor(
    val apiKey: String,
    val environment: Environment = Environment.PROD
) {
    companion object {
        @JvmField
        val VENDOR = Vendor("Sage Business Cloud - Accounting", "Sage Group PLC")
    }

    enum class Environment(val path: String) {
        PROD("https://accounting.sageone.co.za/api/2.0.0"),
        TEST("https://resellers.accounting.sageone.co.za/api/2.0.0")
    }

    @JvmOverloads
    fun offeredTo(
        company: SageOneZAUserCompany,
        regulator: QueryRegulator = QueryRegulator.FORGIVING
    ): AccountingPackager {
        val owner = Owner(
            uid = "<unset>",
            name = company.name,
            foreignID = company.companyId,
            foreignName = company.name
        )
        val credentials = Credentials(
            username = company.username,
            password = company.password,
            apiKey = apiKey,
            companyId = company.companyId
        )
        return SageOneZAPackager(this, owner, credentials)
    }
}
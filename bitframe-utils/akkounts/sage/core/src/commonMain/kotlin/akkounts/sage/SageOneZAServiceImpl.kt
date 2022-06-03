package akkounts.sage

import akkounts.provider.AccountingPackager
import akkounts.provider.Owner
import akkounts.regulation.QueryRegulator
import kotlin.jvm.JvmOverloads

internal class SageOneZAServiceImpl(
    private val config: SageOneZAServiceConfig
) : SageOneZAService {
    override fun offeredTo(
        company: SageOneZAUserCompany,
        regulator: QueryRegulator
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
            apiKey = config.apiKey,
            companyId = company.companyId
        )
        return SageOneZAPackager(owner, credentials, config.environment)
    }

    override fun toString() = "$config".replace("Config", "")
}
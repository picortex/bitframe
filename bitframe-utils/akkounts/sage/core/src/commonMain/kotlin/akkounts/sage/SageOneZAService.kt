package akkounts.sage

import akkounts.provider.AccountingPackager
import akkounts.provider.Vendor
import akkounts.regulation.QueryRegulator
import akkounts.provider.Owner
import akkounts.sage.reports.SageOneZAReportsService
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface SageOneZAService {

    enum class Environment(val path: String) {
        PROD("https://accounting.sageone.co.za/api/2.0.0"),
        TEST("https://resellers.accounting.sageone.co.za/api/2.0.0")
    }

    companion object {
        @JvmField
        val VENDOR = Vendor("Sage Business Cloud - Accounting", "Sage Group PLC")

        @JvmSynthetic
        operator fun invoke(
            config: SageOneZAServiceConfig
        ): SageOneZAService = SageOneZAServiceImpl(config)

        operator fun invoke(
            apiKey: String,
            environment: Environment = Environment.PROD
        ): SageOneZAService = SageOneZAServiceImpl(SageOneZAServiceConfig(apiKey, environment))

        @JvmStatic
        fun create(
            config: SageOneZAServiceConfig
        ): SageOneZAService = SageOneZAServiceImpl(config)

        @JvmOverloads
        @JvmStatic
        fun create(
            apiKey: String,
            environment: Environment = Environment.PROD
        ): SageOneZAService = invoke(apiKey, environment)
    }

    fun offeredTo(company: SageOneZAUserCompany) = offeredTo(company, QueryRegulator.FORGIVING)

    fun offeredTo(
        company: SageOneZAUserCompany,
        regulator: QueryRegulator
    ): AccountingPackager
}
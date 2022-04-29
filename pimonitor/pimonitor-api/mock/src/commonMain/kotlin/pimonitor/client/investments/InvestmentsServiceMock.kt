package pimonitor.client.investments

import bitframe.client.ServiceConfigMock
import bitframe.core.Dao
import bitframe.core.get
import kash.Currency
import kotlinx.datetime.TimeZone
import pimonitor.client.utils.disbursables.disbursements.DisbursementsService
import pimonitor.client.utils.disbursables.disbursements.DisbursementsServiceMock
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentsServiceCore
import pimonitor.core.investments.InvestmentsServiceDaod

class InvestmentsServiceMock(
    val config: ServiceConfigMock,
    private val currency: Currency = Currency.ZAR,
    private val timezone: TimeZone = TimeZone.UTC
) : InvestmentsService(config), InvestmentsServiceCore by InvestmentsServiceDaod(config) {
    private val factory get() = config.daoFactory
    private val disbursableDao: Dao<Investment> by lazy { factory.get() }
    override val disbursements by lazy {
        DisbursementsServiceMock(config, disbursableDao,timezone)
    }
}
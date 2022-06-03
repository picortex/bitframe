package pimonitor.client.interventions

import bitframe.client.ServiceConfigMock
import bitframe.core.Dao
import bitframe.core.get
import kash.Currency
import kotlinx.datetime.TimeZone
import pimonitor.client.utils.disbursables.disbursements.DisbursementsService
import pimonitor.client.utils.disbursables.disbursements.DisbursementsServiceMock
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.InterventionsServiceCore
import pimonitor.core.interventions.InterventionsServiceDaod
import pimonitor.core.investments.Investment

open class InterventionsServiceMock(
    private val config: ServiceConfigMock,
    private val currency: Currency = Currency.ZAR,
    private val timezone: TimeZone = TimeZone.UTC
) : InterventionService(config), InterventionsServiceCore by InterventionsServiceDaod(config, currency, timezone) {
    private val factory get() = config.daoFactory
    private val disbursableDao: Dao<Intervention> by lazy { factory.get() }
    override val disbursements by lazy {
        DisbursementsServiceMock(config, disbursableDao, timezone)
    }
}
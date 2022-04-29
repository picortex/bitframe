package pimonitor.client.utils.disbursables.disbursements

import bitframe.client.ServiceConfigMock
import bitframe.core.Dao
import kotlinx.datetime.TimeZone
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceCore
import pimonitor.core.utils.disbursables.disbursements.DisbursementsServiceDaod

class DisbursementsServiceMock(
    private val config: ServiceConfigMock,
    private val disbursableDao: Dao<Disbursable>,
    private val timezone: TimeZone
) : DisbursementsServiceImpl(config), DisbursementsServiceCore by DisbursementsServiceDaod(config, disbursableDao, timezone)
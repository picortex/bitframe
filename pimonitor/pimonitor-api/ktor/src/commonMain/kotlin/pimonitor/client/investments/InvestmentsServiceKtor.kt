package pimonitor.client.investments

import bitframe.client.ServiceConfigKtor
import bitframe.core.RequestBody
import kotlinx.collections.interoperable.List
import later.Later
import pimonitor.core.business.utils.disbursements.Disbursement
import pimonitor.core.investments.InvestmentFilter
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.CreateInvestmentDisbursementParams
import pimonitor.core.investments.params.CreateInvestmentsParams

class InvestmentsServiceKtor(private val config: ServiceConfigKtor) : InvestmentsService(config) {
    override fun all(rb: RequestBody.Authorized<InvestmentFilter>): Later<List<InvestmentSummary>> {
        TODO("Not yet implemented")
    }

    override fun capture(rb: RequestBody.Authorized<CreateInvestmentsParams>): Later<InvestmentSummary> {
        TODO("Not yet implemented")
    }

    override fun disburse(rb: RequestBody.Authorized<CreateInvestmentDisbursementParams>): Later<Disbursement> {
        TODO("Not yet implemented")
    }
}
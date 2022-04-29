package pimonitor.client.investments

import bitframe.client.ServiceConfigKtor
import bitframe.client.of
import bitframe.core.Identified
import bitframe.core.RequestBody
import io.ktor.client.request.*
import io.ktor.client.statement.*
import later.Later
import later.later
import pimonitor.client.utils.disbursables.DisbursableServiceKtor
import pimonitor.client.utils.disbursables.disbursements.DisbursementsService
import pimonitor.client.utils.disbursables.disbursements.DisbursementsServiceKtor
import pimonitor.client.utils.pathV1
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.utils.disbursables.DisbursableServiceCore
import response.decodeResponseFromString

class InvestmentsServiceKtor(
    private val config: ServiceConfigKtor
) : InvestmentsService(config), DisbursableServiceCore<Investment, InvestmentSummary> by DisbursableServiceKtor(
    config, Investment.serializer(), InvestmentSummary.serializer(), config.pathV1.investments
) {
    private val json get() = config.json
    private val http get() = config.http
    private val path get() = config.pathV1

    override val disbursements: DisbursementsService by lazy { DisbursementsServiceKtor(config, path.investments.disbursements) }

    override fun create(rb: RequestBody.Authorized<InvestmentParams>): Later<Investment> = config.scope.later {
        val res = http.post(path.investments.create) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }

    override fun update(rb: RequestBody.Authorized<Identified<InvestmentParams>>): Later<Investment> = config.scope.later {
        val res = http.post(path.investments.update) {
            setBody(json.of(rb))
        }
        json.decodeResponseFromString(Investment.serializer(), res.bodyAsText()).response()
    }
}
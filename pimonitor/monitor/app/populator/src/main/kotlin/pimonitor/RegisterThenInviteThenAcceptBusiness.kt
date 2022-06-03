package pimonitor

import datetime.Date
import identifier.NameGenerator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import later.await
import pimonitor.client.MonitorApi
import pimonitor.client.utils.disbursables.DisbursableService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.interventions.Intervention
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentType
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import presenters.fields.toInputValue
import kotlin.random.Random

private val demoInvestments = listOf(
    "Asset Capital" to 200000,
    "Working Capital" to 150000,
    "Seed Fund" to 50000,
    "Women Empowerment" to 780000,
)

private val demoInterventions = listOf(
    "Concur the world" to 200000,
    "Eastern coverage" to 150000,
    "Best sellers" to 50000,
    "Top Consultant in South Africa" to 780000,
)

suspend fun MonitorApi.registerThenInviteThenAcceptBusiness(data: Collection<DemoBusiness>) = data.map {
    coroutineScope {
        async {
            val param = CreateMonitoredBusinessParams(
                businessName = it.name,
                contactName = it.contactName,
                contactEmail = it.contactEmail
            )
            val business = businesses.create(param).await().business

            val param2 = InviteToShareReportsParams(
                businessId = business.uid,
                to = it.contactEmail,
                subject = "[STAGING] ${InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT}",
                message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
            )
            val invite = invites.send(param2).await()

            val params3 = AcceptPicortexInviteParams(
                inviteId = invite.uid,
                subdomain = it.domain,
                secret = it.secret
            )
            invites.accept(params3).await()

            val params4 = AcceptSageOneInviteParams(
                inviteId = invite.uid,
                companyId = business.uid,
                username = business.name,
                password = business.name
            )
            
            invites.accept(params4).await()

            demoInvestments.map { inv -> inv.toInvestmentParam(business) }.map { param ->
                investments.create(param).await()
            }.map { inv -> inv.disburseInvestmentRandomly(this@registerThenInviteThenAcceptBusiness) }

            demoInvestments.map { inv -> inv.toInterventionParam(business) }.map { param ->
                interventions.create(param).await()
            }.map { inv -> inv.disburseInterventionsRandomly(this@registerThenInviteThenAcceptBusiness) }
        }
    }
}.awaitAll()

private fun Pair<String, Number>.toInvestmentParam(business: MonitoredBusinessBasicInfo) = InvestmentParams(
    businessId = business.uid,
    name = first,
    type = InvestmentType.values().random().name,
    source = NameGenerator.randomFullName(),
    amount = second.toString(),
    date = Date.today().toIsoFormat(),
    details = "Testing"
)

private fun Pair<String, Number>.toInterventionParam(business: MonitoredBusinessBasicInfo) = InterventionParams(
    businessId = business.uid,
    name = first,
    date = Date.today().toIsoFormat(),
    amount = "$second",
    deadline = Date.today().toIsoFormat(),
    recommendations = "Testing"
)

suspend fun Disbursable.disburseRandomly(service: DisbursableService<*, *>) = coroutineScope {
    if (Random.nextInt(10) < 7) {
        val noOfDisbursements = Random.nextInt(5)
        val list = mutableListOf<Deferred<*>>()
        repeat(noOfDisbursements) {
            val params = DisbursableDisbursementParams(uid, (amount * 0.2).toInputValue(), Date.today().toIsoFormat())
            list.add(async { service.disbursements.create(params).await() })
        }
        list.awaitAll()
    }
}

suspend fun Investment.disburseInvestmentRandomly(api: MonitorApi) = disburseRandomly(api.investments)

suspend fun Intervention.disburseInterventionsRandomly(api: MonitorApi) = disburseRandomly(api.interventions)
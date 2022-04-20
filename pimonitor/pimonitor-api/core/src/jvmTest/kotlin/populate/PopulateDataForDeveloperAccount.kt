package populate

import bitframe.core.signin.SignInParams
import datetime.Date
import identifier.NameGenerator
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentType
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.SignUpIndividualParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import presenters.fields.toInputValue
import kotlin.random.Random
import kotlin.test.Test

class PopulateDataForDeveloperAccount {
    val api = PiMonitorApiTest()

    val developerEmail1 = "ssajja@gmail.com"
    val developerEmail2 = "luge@gmail.com"

    val investments = listOf(
        "Asset Capital" to 200000,
        "Working Capital" to 150000,
        "Seed Fund" to 50000,
        "Women Empowerment" to 780000,
    )

    @Test
    fun should_create_account_and_add_information_for_a_development_account_with_integrated_businesses() = runSequence {
        step("Register dev account(email: $developerEmail1, password: $developerEmail1)") {
            val params = SignUpIndividualParams(
                name = "Steven Sajja",
                email = developerEmail1,
                password = developerEmail1
            )
            api.signUp.signUp(params).await()
        }

        step("Sign in with the dev account") {
            val cred = SignInParams(
                identifier = developerEmail1,
                password = developerEmail1
            )
            api.signIn.signIn(cred).await()
        }

        val business1 = step("Create a business and connect it with picortex dashboard only") {
            val param1 = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Mohammed Majapa",
                contactEmail = developerEmail1
            )
            val res1 = api.businesses.create(param1).await()

            val param2 = InviteToShareReportsParams(
                businessId = res1.business.uid,
                to = developerEmail1,
                subject = "[STAGING] ${InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT}",
                message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
            )
            val res2 = api.invites.send(param2).await()

            val params3 = AcceptPicortexInviteParams(
                inviteId = res2.uid,
                subdomain = "b2b",
                secret = "f225ela32hovtvo4s1bj466j1p"
            )
            val res3 = api.invites.accept(params3).await()

            val params4 = AcceptSageOneInviteParams(
                inviteId = res2.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            api.invites.accept(params4).await()
            res1.business
        }

        step("Create investments for the created business") {
            investments.map { it.toInvestmentParam(business1) }.map { param ->
                api.investments.create(param).await()
            }.map { inv -> inv.disburseRandomly() }
        }

        val business2 = step("Create another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "Cilla's Oven",
                contactName = "Priscilla Sajja",
                contactEmail = developerEmail1
            )
            val res1 = api.businesses.create(param).await()

            val param2 = InviteToShareReportsParams(
                businessId = res1.business.uid,
                to = developerEmail1,
                subject = "[STAGING] ${InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT}",
                message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
            )
            val res2 = api.invites.send(param2).await()

            val params3 = AcceptPicortexInviteParams(
                inviteId = res2.uid,
                subdomain = "b2bdemo",
                secret = "89aqiclvjktp0aa4bgfqpbppf6",
            )
            api.invites.accept(params3).await()
            res1.business
        }

        step("Create yet another investments for the created business") {
            investments.map { it.toInvestmentParam(business2) }.map { param ->
                api.investments.create(param).await()
            }.map { inv -> inv.disburseRandomly() }
        }

        val business3 = step("Create yet again another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = developerEmail1
            )
            api.businesses.create(param).await().business
        }

        step("Create yet again another investments for the created business") {
            investments.map { it.toInvestmentParam(business3) }.map { param ->
                api.investments.create(param).await()
            }.map { inv -> inv.disburseRandomly() }
        }
    }

    @Test
    fun should_create_account_and_add_information_for_a_development_account_with_non_integrated_businesses() = runSequence {
        step("Register dev account(email: $developerEmail2, password: $developerEmail2)") {
            val params = SignUpIndividualParams(
                name = "Lugendo Paul Tulla",
                email = developerEmail2,
                password = developerEmail2
            )
            api.signUp.signUp(params).await()
        }

        step("Sign in with the dev account") {
            val cred = SignInParams(
                identifier = developerEmail2,
                password = developerEmail2
            )
            api.signIn.signIn(cred).await()
        }

        step("Create a business and connect it with picortex dashboard only") {
            val param1 = CreateMonitoredBusinessParams(
                businessName = "Kremlin Limited",
                contactName = "Vladmir Putin",
                contactEmail = "vlad@putin.com"
            )
            api.businesses.create(param1).await()
        }

        step("Create another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "Ruaha Catholic University",
                contactName = "Ben Marucho",
                contactEmail = "ben@marucho.com"
            )
            api.businesses.create(param).await()
        }

        step("Create yet again another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andy@lamax.com"
            )
            api.businesses.create(param).await()
        }
    }

    private fun Pair<String, Number>.toInvestmentParam(business: MonitoredBusinessBasicInfo) = InvestmentParams(
        businessId = business.uid,
        name = first,
        type = InvestmentType.values().random().name,
        source = NameGenerator.randomFullName(),
        amount = second.toString(),
        date = Date.today().toIsoFormat(),
        details = "Testing"
    )

    suspend fun Investment.disburseRandomly() = coroutineScope {
        if (Random.nextInt(10) < 3) {
            val noOfDisbursements = Random.nextInt(5)
            buildList {
                repeat(noOfDisbursements) {
                    val params = DisbursableDisbursementParams(uid, (amount * 0.2).toInputValue(), Date.today().toIsoFormat())
                    add(async { api.investments.createDisbursement(params).await() })
                }
            }.joinAll()
        }
    }
}
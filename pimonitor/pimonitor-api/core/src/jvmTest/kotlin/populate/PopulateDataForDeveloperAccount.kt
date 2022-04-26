package populate

import bitframe.core.signin.SignInParams
import datetime.Date
import identifier.NameGenerator
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
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
import pimonitor.core.signup.params.SignUpIndividualParams
import pimonitor.core.utils.disbursables.Disbursable
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

    val interventions = listOf(
        "Concur the world" to 200000,
        "Eastern coverage" to 150000,
        "Best sellers" to 50000,
        "Top Consultant in South Africa" to 780000,
    )

    @Test
    fun should_populate_data_for_demo() = runSequence {
        val email = "mmajapa@gmail.com"
        step("Register demo account(email: $email, password: $email)") {
            val params = SignUpIndividualParams(
                name = "Mohammed Majapa",
                email = email,
                password = email
            )
            api.signUp.signUp(params).await()
        }

        step("Sign in with the demo account") {
            val cred = SignInParams(
                identifier = email,
                password = email
            )
            api.signIn.signIn(cred).await()
        }

        class DemoList(
            val name: String,
            val domain: String,
            val secret: String,
            val contactName: String = NameGenerator.randomFullName(),
            val contactEmail: String = "support@picortex.com",
        )

        listOf<DemoList>(
            DemoList("Ziyahlanjwa Trading (Pty) Ltd", "ziyahlanjwa", "fbjkfsk4vk34n05nrtr4g3r156"),
            DemoList("Hopefield Raised", "hopefield", "5bsu6s2tk9g79jjskefn8i4kjb"),
            DemoList("Ludada and Associates Orthopaedic Services", "ludada", "a8lch29mut8tp4r5kvm2vtgakc"),
            DemoList("Bleu Rose (Pty) Ltd", "bleurose", "ss9amfj7cdhlag4peu1o74mlkg"),
            DemoList("Curl Chemistry", "curlchemistry", "j811kldtbgm3pd93mip6eudeg5"),
        ).map {
            coroutineScope {
                async {
                    val business = step("Add ${it.name} as a monitored business") {
                        val param = CreateMonitoredBusinessParams(
                            businessName = it.name,
                            contactName = it.contactName,
                            contactEmail = it.contactEmail
                        )
                        api.businesses.create(param).await().business
                    }

                    val invite = step("Invite ${it.name} to share reports") {
                        val param2 = InviteToShareReportsParams(
                            businessId = business.uid,
                            to = it.contactEmail,
                            subject = "[STAGING] ${InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT}",
                            message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
                        )
                        api.invites.send(param2).await()
                    }

                    step("let ${it.name} Accept invite requests for picortex") {
                        val params = AcceptPicortexInviteParams(
                            inviteId = invite.uid,
                            subdomain = it.domain,
                            secret = it.secret
                        )
                        api.invites.accept(params).await()
                    }

                    step("Create investments for ${it.name}") {
                        investments.map { inv -> inv.toInvestmentParam(business) }.map { param ->
                            api.investments.create(param).await()
                        }.map { inv -> inv.disburseInvestmentRandomly() }
                    }

                    step("Create interventions for ${it.name}") {
                        investments.map { inv -> inv.toInterventionParam(business) }.map { param ->
                            api.interventions.create(param).await()
                        }.map { inv -> inv.disburseInterventionsRandomly() }
                    }
                }
            }
        }.joinAll()

        val business1 = step("Create a business and connect it with picortex dashboard only") {
            val extraEmail = "andylamax@programmer.net"
            val param1 = CreateMonitoredBusinessParams(
                businessName = "aSoft LLC",
                contactName = "Anderson Lameck",
                contactEmail = extraEmail
            )
            val res1 = api.businesses.create(param1).await()

            val param2 = InviteToShareReportsParams(
                businessId = res1.business.uid,
                to = extraEmail,
                subject = "[STAGING] ${InviteToShareReportsParams.DEFAULT_INVITE_SUBJECT}",
                message = InviteToShareReportsParams.DEFAULT_INVITE_MESSAGE
            )
            val res2 = api.invites.send(param2).await()

            val params3 = AcceptPicortexInviteParams(
                inviteId = res2.uid,
                subdomain = "ludada",
                secret = "a8lch29mut8tp4r5kvm2vtgakc",
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
            }.map { inv -> inv.disburseInvestmentRandomly() }
        }

        step("Create interventions for the created business") {
            investments.map { it.toInterventionParam(business1) }.map { param ->
                api.interventions.create(param).await()
            }.map { inv -> inv.disburseInterventionsRandomly() }
        }
    }

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
            }.map { inv -> inv.disburseInvestmentRandomly() }
        }

        step("Create interventions for the created business") {
            investments.map { it.toInterventionParam(business1) }.map { param ->
                api.interventions.create(param).await()
            }.map { inv -> inv.disburseInterventionsRandomly() }
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
            }.map { inv -> inv.disburseInvestmentRandomly() }
        }

        step("Create yet another interventions for the created business") {
            investments.map { it.toInterventionParam(business2) }.map { param ->
                api.interventions.create(param).await()
            }.map { inv -> inv.disburseInterventionsRandomly() }
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
            }.map { inv -> inv.disburseInvestmentRandomly() }
        }

        step("Create yet again another interventions for the created business") {
            investments.map { it.toInterventionParam(business3) }.map { param ->
                api.interventions.create(param).await()
            }.map { inv -> inv.disburseInterventionsRandomly() }
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
            buildList {
                repeat(noOfDisbursements) {
                    val params = DisbursableDisbursementParams(uid, (amount * 0.2).toInputValue(), Date.today().toIsoFormat())
                    add(async { service.createDisbursement(params).await() })
                }
            }.joinAll()
        }
    }

    suspend fun Investment.disburseInvestmentRandomly() = disburseRandomly(api.investments)

    suspend fun Intervention.disburseInterventionsRandomly() = disburseRandomly(api.interventions)
}
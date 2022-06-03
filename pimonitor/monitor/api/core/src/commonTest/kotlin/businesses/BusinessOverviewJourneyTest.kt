package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import later.await
import pimonitor.client.MonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.overview.MonitoredBusinessOverview
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Ignore
import kotlin.test.Test

class BusinessOverviewJourneyTest {
    val api = MonitorApiTest()

    @Test
    @Ignore
    fun should_load_operational_only_overview_of_a_picortex_only_integrated_business() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "PiCortex Int Ltd",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInParams(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signIn.signIn(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        val business = step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net"
            )
            val business = api.businesses.create(params).await()
            expect(business.params.businessName).toBe("aSoft Ltd")
            business
        }

        val invite = step("Send an invite to the newly created business") {
            val summary = business.summary
            val params = InviteToShareReportsParams(summary)
            val invite = api.invites.send(params).await()
            expect(invite.invitedBusinessId).toBe(summary.uid)
            invite
        }

        step("Accept invite by capturing picortex credentials") {
            val params = AcceptPicortexInviteParams(
                inviteId = invite.uid,
                subdomain = "b2b",
                secret = "f225ela32hovtvo4s1bj466j1p"
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(invite.uid)
        }

        step("ensure overview that is coming back from the api is Operational Only overview") {
            val overview = api.businessOverview.load(LoadInfoParams(business.summary.uid)).await()
            expect(overview).toBe<MonitoredBusinessOverview.OperationalOnly>()
        }
    }

    @Test
    fun should_load_financial_overview_only_of_a_business_integrated_with_sage_only() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "PiCortex Int Ltd",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInParams(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signIn.signIn(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        val business = step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net"
            )
            val business = api.businesses.create(params).await()
            expect(business.params.businessName).toBe("aSoft Ltd")
            business
        }

        val invite = step("Send an invite to the newly created business") {
            val summary = business.summary
            val params = InviteToShareReportsParams(summary)
            val invite = api.invites.send(params).await()
            expect(invite.invitedBusinessId).toBe(summary.uid)
            invite
        }

        step("Accept invite by capturing sage credentials") {
            val params = AcceptSageOneInviteParams(
                inviteId = invite.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(invite.uid)
        }

        step("ensure overview that is coming back from the api is Financial only Overview") {
            val overview = api.businessOverview.load(LoadInfoParams(business.summary.uid)).await()
            expect(overview).toBe<MonitoredBusinessOverview.FinancialOnly>()
        }
    }

    @Test
    @Ignore
    fun should_load_operational_and_financial_overview_of_a_business_integrted_with_both() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "PiCortex Int Ltd",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInParams(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signIn.signIn(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        val business = step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net"
            )
            val business = api.businesses.create(params).await()
            expect(business.params.businessName).toBe("aSoft Ltd")
            business
        }

        val invite = step("Send an invite to the newly created business") {
            val summary = business.summary
            val params = InviteToShareReportsParams(summary)
            val invite = api.invites.send(params).await()
            expect(invite.invitedBusinessId).toBe(summary.uid)
            invite
        }

        step("Accept invite by capturing picortex credentials") {
            val params = AcceptPicortexInviteParams(
                inviteId = invite.uid,
                subdomain = "b2b",
                secret = "f225ela32hovtvo4s1bj466j1p"
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(invite.uid)
        }

        step("Accept invite by capturing sage credentials") {
            val params = AcceptSageOneInviteParams(
                inviteId = invite.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(invite.uid)
        }

        step("ensure overview that is coming back from the api is Operational Only overview") {
            val overview = api.businessOverview.load(LoadInfoParams(business.summary.uid)).await()
            expect(overview).toBe<MonitoredBusinessOverview.FinancialAndOperational>()
        }
    }

    @Test
    fun should_load_overview_not_integrated_overview_of_a_non_integrated_business() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "Invitor LLC",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInParams(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signIn.signIn(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }


        val business = step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net",
            )
            val business = api.businesses.create(params).await().business
            expect(business.name).toBe("aSoft Ltd")
            business
        }

        step("load overview of the created business") {
            val overview = api.businessOverview.load(LoadInfoParams(businessId = business.uid)).await()
            expect(overview).toBe<MonitoredBusinessOverview.MissingData>()
        }
    }
}
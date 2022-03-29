package populate

import bitframe.core.signin.SignInCredentials
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.IndividualSignUpParams
import kotlin.test.Test

class PopulateDataForDeveloperAccount {
    val api = PiMonitorApiTest()

    val developerEmail1 = "ssajja@gmail.com"
    val developerEmail2 = "luge@gmail.com"


    @Test
    fun should_create_account_and_add_information_for_a_development_account_with_integrated_businesses() = runSequence {
        step("Register dev account(email: $developerEmail1, password: $developerEmail1)") {
            val params = IndividualSignUpParams(
                name = "Steven Sajja",
                email = developerEmail1,
                password = developerEmail1
            )
            api.signUp.signUp(params).await()
        }

        step("Sign in with the dev account") {
            val cred = SignInCredentials(
                identifier = developerEmail1,
                password = developerEmail1
            )
            api.signIn.signIn(cred).await()
        }

        step("Create a business and connect it with picortex dashboard only") {
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
        }

        step("Create another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "Cilla's Oven",
                contactName = "Priscilla Sajja",
                contactEmail = developerEmail1
            )
            api.businesses.create(param).await()

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
            val res3 = api.invites.accept(params3).await()
        }

        step("Create yet again another business") {
            val param = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = developerEmail1
            )
            api.businesses.create(param).await()
        }
    }

    @Test
    fun should_create_account_and_add_information_for_a_development_account_with_non_integrated_businesses() = runSequence {
        step("Register dev account(email: $developerEmail2, password: $developerEmail2)") {
            val params = IndividualSignUpParams(
                name = "Lugendo Paul Tulla",
                email = developerEmail2,
                password = developerEmail2
            )
            api.signUp.signUp(params).await()
        }

        step("Sign in with the dev account") {
            val cred = SignInCredentials(
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
                businessName = "RUCU",
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
}
package profile

import bitframe.core.profile.params.ChangePasswordParams
import bitframe.core.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.signup.RawIndividualSignUpParams
import kotlin.test.Test

class ChangePasswordFlow {
    val api: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_be_to_successfully_change_password() = runTest {
        // STEP 1. If not registered, register into the application
        val params1 = RawIndividualSignUpParams(
            name = "individual01",
            email = "test@individual.com",
            password = "password1"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("individual01")

        // STEP 2. Sign into the application
        val params2 = SignInCredentials(
            identifier = "test@individual.com",
            password = "password1"
        )
        val res2 = api.signIn.signIn(params2).await()
        expect(res2.user.name).toBe("individual01")

        // STEP 3. Change password
        val params3 = ChangePasswordParams(
            previous = "password1",
            current = "password2"
        )
        val res3 = api.profile.changePassword(params3).await()
        expect(res3.current).toBe("password2")

        // STEP 4. Logout of the application
        val res4 = api.session.signOut()

        // STEP 5. Sign in with new password
        val params5 = SignInCredentials(
            identifier = "test@individual.com",
            password = "password2"
        )
        val res5 = api.signIn.signIn(params5).await()
        expect(res5.user.name).toBe("individual01")

        // STEP 6. Ensure signed in
        expect(api.session.currentUser?.name).toBe("individual01")
    }
}
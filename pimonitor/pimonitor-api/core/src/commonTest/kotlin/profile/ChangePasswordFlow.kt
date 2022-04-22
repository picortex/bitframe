package profile

import bitframe.core.profile.params.ChangePasswordParams
import bitframe.core.signin.SignInParams
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.signup.params.SignUpIndividualParams
import kotlin.test.Test

class ChangePasswordFlow {
    val api: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_be_to_successfully_change_password() = runTest {
        // STEP 1. If not registered, register into the application
        val time = Clock.System.now()
        val params1 = SignUpIndividualParams(
            name = "Test $time Individual",
            email = "test@individual${time.toEpochMilliseconds()}.com",
            password = "test@individual${time.toEpochMilliseconds()}.com",
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("Test $time Individual")

        // STEP 2. Sign into the application
        val params2 = SignInParams(
            identifier = "test@individual${time.toEpochMilliseconds()}.com",
            password = "test@individual${time.toEpochMilliseconds()}.com"
        )
        val res2 = api.signIn.signIn(params2).await()
        expect(res2.user.name).toBe("Test $time Individual")

        val now = Clock.System.now()
        // STEP 3. Change password
        val params3 = ChangePasswordParams(
            previous = "test@individual${time.toEpochMilliseconds()}.com",
            current = "test@individual${now.toEpochMilliseconds()}.com"
        )
        val res3 = api.profile.changePassword(params3).await()
        expect(res3.current).toBe("test@individual${now.toEpochMilliseconds()}.com")

        // STEP 4. Logout of the application
        val res4 = api.session.signOut()

        // STEP 5. Sign in with new password
        val params5 = SignInParams(
            identifier = "test@individual${time.toEpochMilliseconds()}.com",
            password = "test@individual${now.toEpochMilliseconds()}.com"
        )
        val res5 = api.signIn.signIn(params5).await()
        expect(res5.user.name).toBe("Test $time Individual")

        // STEP 6. Ensure signed in
        expect(api.session.currentUser?.name).toBe("Test $time Individual")
    }
}
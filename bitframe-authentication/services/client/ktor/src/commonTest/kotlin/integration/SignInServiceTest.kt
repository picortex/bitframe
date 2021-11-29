package integration

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore // TODO: Move tests to bitframe instance tests
open class SignInServiceTest {
    private val service: SignInService = SignInServiceKtor(CONFIG_UNDER_TEST)

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(SignInCredentials("ssajja@gmail.com", "pass1")).await()
        expect(conundrum.user.tag).toBe("Steven Sajja")
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        try {
            service.signIn(SignInCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("User with loginId=username, not found")).toBe(true)
        }
    }
}
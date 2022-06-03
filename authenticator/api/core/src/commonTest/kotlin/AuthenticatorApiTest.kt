import authenticator.AuthenticatorApi
import authenticator.AuthenticatorApiMock
import bitframe.core.signin.SignInParams
import bitframe.core.users.RegisterUserParams
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

class AuthenticatorApiTest {
    val authenticator: AuthenticatorApi = AuthenticatorApiMock()

    @Test
    fun should_easily_sign_in() = runTest {
        val params = SignInParams(
            identifier = "andy1@lamax.com",
            password = "andy2@lamax.com"
        )
        val err = expectFailure {
            authenticator.signIn(params).await()
        }
        expect(err.message).toBe("Entity(identifier=andy1@lamax.com) is not found in dao")
    }

    @Test
    fun should_easily_sign_out() = runTest {
        authenticator.signOut()
    }

    @Test
    fun should_register_a_user_easily() = runTest {
        val params = RegisterUserParams(
            userName = "Anderson",
            userIdentifier = "andy@lamax.com",
            userPassword = "andy@lamax.com",
            userType = "TEST_USER",
            spaceName = "TEST_SPACE",
            spaceType = "TEST",
            spaceScope = "TEST"
        )
        val res = authenticator.users.register(params).await()
        expect(res.user.name).toBe("Anderson")
    }
}
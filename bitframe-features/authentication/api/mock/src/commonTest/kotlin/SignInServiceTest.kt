import bitframe.client.signin.SignInService
import bitframe.client.signin.SignInServiceMock
import bitframe.core.signin.SignInRawParams
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

open class SignInServiceTest {
    private val service: SignInService = SignInServiceMock()

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        val err = expectFailure {
            service.signIn(SignInRawParams("user01@test.com", "password")).await()
        }
        expect(err.message).toBe("Entity(identifier=user01@test.com) is not found in dao")
    }

    @Test
    fun should_accept_to_authenticate_with_a_valid_identity() = runTest {
        val err = expectFailure {
            service.signIn(SignInRawParams("username", "password")).await()
        }
        expect(err.message).toBe("Identifier with value username is neither a valid email or a valid phone number")
    }
}
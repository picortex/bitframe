package unit

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDaoInMemory
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Test

class SignInServiceTest {

    val service: SignInService = SignInServiceImpl(
        usersDao = UsersDaoInMemory(),
        spacesDao = SpacesDaoInMemory(),
        ServiceConfig.DEFAULT
    )

    @Test
    fun should_fail_when_credentials_are_empty() = runTest {
        val credentials = SignInCredentials("", "")
        val err = expectFailure {
            service.signIn(credentials).await()
        }
        expect(err.message).toBe("loginId (i.e. email/phone/username), must not be empty")
    }

    @Test
    fun should_fail_when_sign_in_alias_password_is_empty() = runTest {
        val credentials = SignInCredentials("john", "")
        val err = expectFailure {
            service.signIn(credentials).await()
        }
        expect(err.message).toBe("Password must not be empty")
    }
}
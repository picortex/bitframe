@file:Suppress("ClassName")

package acceptance.authentication

import acceptance.utils.AcceptanceTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.monitors.SignUpParams
import pimonitor.test
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignUp : AcceptanceTest() {

    @Nested
    inner class `Individual Registration` {
        // Given a Person with details
        private val person = SignUpParams.Individual(
            name = "John Doe", email = "john.doe@johndoeinc.com", password = "1234"
        )

        @Test
        fun should_should_be_able_to_sign_individually() = application.test {
            val signUpScreen = openSignUpScreen()
            signUpScreen.signUp(person)
            signUpScreen.expectUserToBeRegistered()
        }
    }

    @Nested
    inner class `Organisational Registration` {
        private val params = SignUpParams.Business(
            businessName = "John Doe Inc.",
            individualName = "John Doe",
            individualEmail = "john.doe@johndoeinc.com",
            password = "1234"
        )

        @Test
        fun should_register_a_new_user() = application.test {
            val signUpScreen = openSignUpScreen()
            signUpScreen.signUp(with = params)
            signUpScreen.expectUserToBeRegistered()
        }
    }
}
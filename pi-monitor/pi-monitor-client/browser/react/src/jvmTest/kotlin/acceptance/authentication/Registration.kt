@file:Suppress("ClassName")

package acceptance.authentication

import acceptance.utils.AcceptanceTest
import expect.expect
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.MonitorBusinessParams
import pimonitor.MonitorPersonParams
import pimonitor.screens.api.toBeVisible
import pimonitor.test
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Registration : AcceptanceTest() {

    @Test
    fun should_start_with_the_selection_screen() = application.test {
        val signUpScreen = openSignUpScreen()
        expect(signUpScreen).toBeVisible()
    }

    @Nested
    inner class `Individual Registration` {
        // Given a Person with details
        private val person = MonitorPersonParams(
            name = "John Doe",
            email = "john.doe@johndoeinc.com",
            password = "1234"
        )

        @Test
        fun should_should_be_able_to_sign_individually() = application.test {
            val signUpScreen = openSignUpScreen()
            signUpScreen.signUpIndividuallyAs(person)
            signUpScreen.expectUserToBeRegistered()
        }
    }

    @Nested
    inner class `Organisational Registration` {
        // Given
        private val person = MonitorPersonParams(
            name = "John Doe",
            email = "john.doe@johndoeinc.com",
            password = "1234"
        )

        // Given
        private val business = MonitorBusinessParams(
            name = "John Doe Inc.",
            email = "support@johndoeinc.com"
        )

        @Test
        fun should_register_a_new_user() = application.test {
            val signUpScreen = openSignUpScreen()
            signUpScreen.signUpAs(person, representing = business)
            signUpScreen.expectUserToBeRegistered()
        }
    }
}
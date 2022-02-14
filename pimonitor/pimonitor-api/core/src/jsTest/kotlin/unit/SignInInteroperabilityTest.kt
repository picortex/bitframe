package unit

import bitframe.client.globalThis
import bitframe.client.jso
import bitframe.core.signin.SignInResult
import expect.expect
import kotlinx.coroutines.test.TestResult
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import kotlin.js.Promise
import kotlin.test.Test

class SignInInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiMock()

    init {
        globalThis.signInService = service.signIn
    }

    val signInService = globalThis.signInService

    @Test
    fun should_take_in_raw_email_password_credentials(): TestResult {
        val promise = signInService.signIn(jso {
            email = "sign_in_john@doe.com"
            password = "john"
        }).asPromise() as Promise<SignInResult>

        return promise.catch {
            expect(it.message).toBe("Entity(identifier=sign_in_john@doe.com) is not found in dao")
        }
    }

    @Test
    fun should_take_in_raw_phone_password_credentials(): TestResult {
        val promise = signInService.signIn(jso {
            phone = "752748674"
            password = "john"
        }).asPromise() as Promise<SignInResult>

        return promise.catch {
            expect(it.message).toBe("Entity(identifier=255752748674) is not found in dao")
        }
    }

    @Test
    fun should_take_in_raw_identifier_password_credentials(): TestResult {
        val promise = signInService.signIn(jso {
            identifier = "identifier@identifier.com"
            password = "john"
        }).asPromise() as Promise<SignInResult>

        return promise.catch {
            expect(it.message).toBe("Entity(identifier=identifier@identifier.com) is not found in dao")
        }
    }

    @Test
    fun should_return_a_reasonable_message_whn_invalid_identifier_are_passed(): TestResult {
        val promise = signInService.signIn(jso {
            identifier = "identifier"
            password = "john"
        }).asPromise() as Promise<SignInResult>

        return promise.catch {
            expect(it.message).toBe("Identifier with value identifier is neither a valid email or a valid phone number")
        }
    }
}
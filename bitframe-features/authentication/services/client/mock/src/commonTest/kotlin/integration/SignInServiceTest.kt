package integration

import bitframe.actors.spaces.Space
import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.signin.SignInCredentials
import bitframe.actors.users.User
import expect.expect
import kotlinx.collections.interoperable.listOf
import kotlinx.coroutines.test.runTest
import later.await
import kotlin.test.Test

open class SignInServiceTest {
    private val config = SignInServiceMockConfig(
        users = mutableListOf(
            User(
                uid = "user-1",
                name = "User One",
                tag = "user-one",
                contacts = listOf(),// Contacts.of("user01@test.com"),
                photoUrl = null,
                spaces = listOf(
                    Space(
                        uid = "space-1",
                        name = "User One Space",
                        scope = "",
                        type = ""
                    )
                )
            )
        )
    )

    private val service = SignInServiceMock(config)

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(SignInCredentials("user01@test.com", "pass1")).await()
        expect(conundrum.user.tag).toBe("user-one")
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
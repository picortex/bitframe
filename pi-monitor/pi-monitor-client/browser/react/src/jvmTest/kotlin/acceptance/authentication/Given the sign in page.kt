@file:Suppress("ClassName")

package acceptance.authentication

import bitframe.authentication.LoginCredentials
import expect.expect
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import pimonitor.Application
import pimonitor.WebApplication
import pimonitor.screens.api.toBeVisible
import pimonitor.test
import java.nio.file.Path

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class `Given the sign in page` {
    companion object {
        private val dockerImage = ImageFromDockerfile()
            .withDockerfile(Path.of("build/websites/js/Dockerfile"))

        @Container
        private val container: GenericContainer<*> = GenericContainer<Nothing>(dockerImage).withExposedPorts(80)

        private val urlUnderTest get() = "http://${container.containerIpAddress}:${container.firstMappedPort}"
        private val application: Application get() = WebApplication(urlUnderTest)
    }

    @Test
    fun should_be_able_to_just_open_the_application() = application.test {
        val landingScreen = openLandingScreen()
        expect(landingScreen).toBeVisible()
    }

    @Nested
    inner class `When users with valid credentials attempts to login` {
        @Test
        fun they_should_be_logged_in() = application.test {
            val signInScreen = openLandingScreen().clickSignInButton()
            signInScreen.loginWith(LoginCredentials("user1", "pass1"))
            expectUserToBeLoggedIn()
        }
    }

    @Nested
    inner class `When users with invalid credentials attempts to login` {

        @ParameterizedTest
        @CsvSource("username,password", "user1,password", "user2,password")
        fun they_should_not_succeed(username: String, password: String) = application.test {
            val signInScreen = openLandingScreen().clickSignInButton()
            expect(signInScreen).toBeVisible()
            signInScreen.loginWith(LoginCredentials(username, password))
            expectUserToNotBeLoggedIn()
        }
    }
}
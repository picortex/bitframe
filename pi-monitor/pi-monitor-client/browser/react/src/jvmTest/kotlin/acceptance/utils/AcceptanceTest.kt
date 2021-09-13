package acceptance.utils

import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import pimonitor.Application
import pimonitor.WebApplication
import java.nio.file.Path

open class AcceptanceTest {
    enum class TestMode {
        DEV, CI, CD
    }

    companion object {
        val mode = try {
            TestMode.valueOf(System.getenv("TEST_MODE"))
        } catch (err: Throwable) {
            println("Couldn't get test mode. Defaulting to TEST_MODE=DEV")
            TestMode.DEV
        }
        private val dockerImage = ImageFromDockerfile()
            .withDockerfile(Path.of("build/websites/js/Dockerfile"))

        @Container
        private val container: GenericContainer<*> = GenericDisableableContainer<Nothing>(dockerImage!!).apply {
            withExposedPorts(80)
            isActive(mode == TestMode.CI)
        }

        private val urlUnderTest
            get() = when (mode) {
                TestMode.DEV -> "http://localhost:8080"
                TestMode.CI -> "http://${container.containerIpAddress}:${container.firstMappedPort}"
                TestMode.CD -> TODO("Setup CD environment to run tests before release")
            }

        val application: Application get() = WebApplication(urlUnderTest)
    }
}
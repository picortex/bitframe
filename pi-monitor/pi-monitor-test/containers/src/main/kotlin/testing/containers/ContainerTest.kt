package testing.containers

import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import java.nio.file.Path

interface ContainerTest {
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

        private val dockerFilePath get() = RootProjectDir.getPath() + "/pi-monitor/pi-monitor-server/build/binaries/Dockerfile"

        private val dockerImage = ImageFromDockerfile()
            .withDockerfile(Path.of(dockerFilePath))

        @Container
        private val container: GenericContainer<*> = GenericDisableableContainer<Nothing>(dockerImage!!).apply {
            withExposedPorts(8080)
            isActive(mode == TestMode.CI)
        }

        val urlUnderTest
            get() = when (mode) {
                TestMode.DEV -> "http://localhost:8080"
                TestMode.CI -> "http://${container.containerIpAddress}:${container.firstMappedPort}"
                TestMode.CD -> TODO("Setup CD environment to run tests before release")
            }
    }
}
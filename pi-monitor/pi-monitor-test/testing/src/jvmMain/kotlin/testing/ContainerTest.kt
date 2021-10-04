package testing

import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import testing.containers.GenericDisableableContainer
import testing.containers.RootProjectDir
import java.nio.file.Path

actual open class ContainerTest {
    actual companion object {
        actual val mode = try {
            TestMode.valueOf(System.getenv("TEST_MODE"))
        } catch (err: Throwable) {
            println("Couldn't get test mode. Defaulting to TEST_MODE=DEV")
            TestMode.DEV
        }

        private val dockerFilePath = RootProjectDir.getPath() + "/pi-monitor/pi-monitor-server/build/binaries/Dockerfile"

        private val dockerImage = ImageFromDockerfile()
            .withDockerfile(Path.of(dockerFilePath))

        @Container
        private val container: GenericContainer<*> = GenericDisableableContainer<Nothing>(dockerImage!!).apply {
            withExposedPorts(8080)
            isActive(mode == TestMode.CI)
        }

        actual val urlUnderTest
            get() = when (mode) {
                TestMode.DEV -> "http://localhost:8080"
                TestMode.CI -> "http://${container.containerIpAddress}:${container.firstMappedPort}"
                TestMode.CD -> TODO("Setup CD environment to run tests before release")
            }
    }
}
package pimonitor.testing

import bitframe.testing.ContainerTest
import bitframe.testing.TestMode
import bitframe.testing.containers.GenericDisableableContainer
import bitframe.testing.containers.RootProjectDir
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import java.nio.file.Path

actual open class PiMonitorContainerTest : ContainerTest() {
    actual companion object {
        actual val mode get() = ContainerTest.mode

        private val dockerFilePath = RootProjectDir.getPath() + "/pi-monitor/pi-monitor-server/build/binaries/Dockerfile"

        private val dockerImage = ImageFromDockerfile().withDockerfile(Path.of(dockerFilePath))

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
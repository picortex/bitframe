package acceptance.authentication

import expect.expect
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.nio.file.Path
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
class SignUpTestContainer {

    companion object {
        private val dockerImage = ImageFromDockerfile()
            .withDockerfile(Path.of("build/websites/js/Dockerfile"))

        @Container
        val container = GenericContainer<Nothing>(dockerImage)
    }

    @Test
    fun print_current_dir() {
        expect(container.isRunning).toBe(true)
    }
}
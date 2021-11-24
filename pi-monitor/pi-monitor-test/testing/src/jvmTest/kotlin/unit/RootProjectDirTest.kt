package unit

import expect.expect
import pimonitor.testing.containers.RootProjectDir
import kotlin.test.Test

class RootProjectDirTest {

    @Test
    fun should_return_the_root_project_path() {
        val validPath = "/media/andylamax/workspace/PiCortex/bitframe"
        val hypotheticalPath = "$validPath/people/test"
        expect(validPath).toBe(RootProjectDir.parse(hypotheticalPath))
    }

    @Test
    fun should_be_able_to_parse_in_any_environment() {
        val validPath = "/home/runner/work/bitframe/bitframe"
        val ciPath = "$validPath/bitframe-client/sdks/core/src/commonMain/kotlin/bitframe/authentication/"
        expect(validPath).toBe(RootProjectDir.parse(ciPath))
    }
}
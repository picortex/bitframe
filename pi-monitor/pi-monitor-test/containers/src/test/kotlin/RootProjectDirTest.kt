import expect.expect
import testing.RootProjectDir
import kotlin.test.Ignore
import kotlin.test.Test

class RootProjectDirTest {

    @Ignore("This works only on a specific machine")
    @Test
    fun should_return_the_root_project_path() {
        expect("/media/andylamax/workspace/PiCortex/bitframe").toBe(RootProjectDir.getPath())
    }
}
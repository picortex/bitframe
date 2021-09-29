package testing

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

class RootProjectDir {
    companion object {
        fun getPath(): String {
            val path = Path(".").absolutePathString()
            val parentDir = path.split("/bitframe/")
            return Path(parentDir.first() + "/bitframe").absolutePathString()
        }
    }
}
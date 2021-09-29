package testing

import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

class RootProjectDir {
    companion object {
        fun getPath(): String {
            val path = Path(".").absolutePathString().replace("/bitframe/bitframe/", "/bitframe/")
            val parentDir = path.split("/bitframe/")
            return Path(parentDir.first() + "/bitframe").absolutePathString()
        }
    }
}
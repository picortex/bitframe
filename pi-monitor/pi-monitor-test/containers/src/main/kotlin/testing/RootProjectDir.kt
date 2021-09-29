package testing

import kotlin.io.path.Path
import kotlin.io.path.absolute
import kotlin.io.path.absolutePathString

class RootProjectDir {
    companion object {
        fun getPath(): String = parse(Path(".").absolutePathString())

        fun parse(path: String): String = if (path.contains("/bitframe/bitframe/")) {
            val parentDir = path.split("/bitframe/bitframe/")
            parentDir.first() + "/bitframe/bitframe"
        } else {
            val parentDir = path.split("/bitframe/")
            parentDir.first() + "/bitframe"
        }
    }
}
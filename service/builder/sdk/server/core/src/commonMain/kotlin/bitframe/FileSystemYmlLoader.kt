package bitframe

import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import net.mamoe.yamlkt.Yaml
import okio.FileNotFoundException
import okio.FileSystem
import okio.Path.Companion.toPath

class FileSystemYmlLoader<C : ServerConfiguration>(
    val fs: FileSystem,
    val appRoot: String,
    val config: String,
    val serializer: KSerializer<C>
) : ConfigLoader<C> {
    private val appRootPath = appRoot.toPath().takeIf {
        fs.exists(it)
    } ?: throw FileNotFoundException("Missing app root dir: $appRoot")

    private val configPath = (appRootPath / "config" / "$config.yml").takeIf {
        fs.exists(it)
    } ?: throw FileNotFoundException("Missing config file at $appRoot/config/$config.yml")

    override fun load(): C {
        val loadedConfig = fs.read(configPath) {
            readUtf8()
        }
        return Yaml.decodeFromString(serializer, loadedConfig)
    }
}
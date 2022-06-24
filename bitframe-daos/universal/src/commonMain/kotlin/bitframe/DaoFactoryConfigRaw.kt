package bitframe

import bitframe.exceptions.IllegalConfiguration
import com.akuleshov7.ktoml.Toml
import com.akuleshov7.ktoml.TomlConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

@Serializable
data class DaoFactoryConfigRaw(
    val instance: String?,
    override val host: String? = null,
    override val username: String? = null,
    override val password: String? = null,
    override val database: String? = null,
    @SerialName("simulation-time") override val simulationTime: Long? = null
) : DaoFactoryMockConfigRaw, DaoFactoryMongoConfigRaw {
    companion object {
        @JvmStatic
        @JvmName("from")
        operator fun invoke(fs: FileSystem, appDir: String?, appConf: String?): DaoFactoryConfigRaw {
            if (appDir == null) throw IllegalConfiguration("APP_DIR is null, please make sure environment variable APP_DIR is set")
            if (appConf == null) throw IllegalConfiguration("APP_CONF is null, please make sure environment variable APP_CONF is set")
            val appDirPath = appDir.toPath()
            if (!fs.exists(appDirPath)) throw IllegalConfiguration("APP_DIR $appDirPath is not a valid path")
            val appConfPath = appDirPath / "configs" / appConf
            if (!fs.exists(appConfPath)) throw IllegalConfiguration("APP_CONF $appConfPath is not a valid path")
            val databaseConfPath = appConfPath / "database.toml"
            if (!fs.exists(databaseConfPath)) throw IllegalConfiguration("database.toml file is missing at $databaseConfPath")
            val content = fs.read(databaseConfPath) { readUtf8() }
            return Toml(TomlConfig(ignoreUnknownNames = true)).decodeFromString(content)
        }
    }
}
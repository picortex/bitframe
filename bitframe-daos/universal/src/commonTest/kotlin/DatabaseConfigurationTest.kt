import bitframe.daos.config.DatabaseConfigurationRaw
import expect.expect
import expect.expectFunction
import kotlin.test.Test
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem

class DatabaseConfigurationTest {
    private val fs: FileSystem = FakeFileSystem()

    companion object {
        private fun FileSystem.makeDatabaseConfigFileWith(content: String) {
            val appDir = "/app".toPath()
            val testConfigDir = appDir / "configs" / "test"
            createDirectories(testConfigDir)
            val databaseConf = testConfigDir / "database.toml"
            write(databaseConf, mustCreate = true) { writeUtf8(content) }
        }
    }

    @Test
    fun should_fail_with_descriptive_errors() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mock"
            simulation-time = 1000
        """.trimIndent()
        )
        val appDirError = expectFunction {
            DatabaseConfigurationRaw(fs, null, null)
        }.toFail()

        expect(appDirError.message).toBe("APP_DIR is null, please make sure environment variable APP_DIR is set")

        val appConfError = expectFunction {
            DatabaseConfigurationRaw(fs, "/app", null)
        }.toFail()
        expect(appConfError.message).toBe("APP_CONF is null, please make sure environment variable APP_CONF is set")
    }

    @Test
    fun should_be_able_to_read_a_mock_database_config_from_a_config_file() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mock"
            simulation-time = 1000
        """.trimIndent()
        )

        val config = DatabaseConfigurationRaw(fs, "/app", "test")
        expect(config.simulationTime).toBe(1000L)
    }

    @Test
    fun should_be_able_to_read_a_mongo_database_config_from_a_config_file() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mongo"
            host = "localhost:2727"
            username = "username"
            password = "password"
            database = "database"
        """.trimIndent()
        )

        val config = DatabaseConfigurationRaw(fs, "/app", "test")
        expect(config.username).toBe("username")
    }
}
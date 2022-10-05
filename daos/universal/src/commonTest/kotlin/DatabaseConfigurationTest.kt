import bitframe.core.MockDaoFactory
import bitframe.daos.config.DaoFactory
import bitframe.daos.config.DatabaseConfigurationRaw
import bitframe.server.MongoDaoFactory
import expect.expect
import expect.expectFunction
import expect.toBe
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

    @Test
    fun should_be_able_to_create_a_mock_instance_without_a_configured_simulation_time() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mock"
        """.trimIndent()
        )
        val factory = DaoFactory(fs, "/app", "test")
        expect(factory).toBe<MockDaoFactory>()
    }

    @Test
    fun should_be_able_to_create_a_mock_dao_factory_instance_with_a_configured_simulation_time() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mock"
            simulation-time = 1000
        """.trimIndent()
        )
        val factory = DaoFactory(fs, "/app", "test")
        expect(factory).toBe<MockDaoFactory>()
    }

    @Test
    fun should_be_able_to_create_a_mongo_dao_factory_instance_without_a_database_config_value() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mongo"
            host = "localhost:2727"
            username = "username"
            password = "password"
        """.trimIndent()
        )

        val factory = DaoFactory(fs, "/app", "test")
        expect(factory).toBe<MongoDaoFactory>()
    }

    @Test
    fun should_be_able_to_create_a_mongo_dao_factory_instance_with_a_database_config_value() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mongo"
            host = "localhost:2727"
            username = "username"
            password = "password"
            database = "test"
        """.trimIndent()
        )

        val factory = DaoFactory(fs, "/app", "test")
        expect(factory).toBe<MongoDaoFactory>()
    }

    @Test
    fun should_fail_to_create_a_mongo_dao_factory_instance_with_a_missing_important_config_value() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mongo"
            username = "username"
            password = "password"
            database = "test"
        """.trimIndent()
        )

        val err = expectFunction { DaoFactory(fs, "/app", "test") }.toFail()
        expect(err.message).toBe("database.host for a mongo database must be provided")
    }

    @Test
    fun should_fail_to_create_an_unsupported_dao_factory() {
        fs.makeDatabaseConfigFileWith(
            """
            instance = "mysql"
            username = "username"
            password = "password"
            database = "test"
        """.trimIndent()
        )

        val err = expectFunction { DaoFactory(fs, "/app", "test") }.toFail()
        expect(err.message).toBe("Unsupported database instance mysql")
    }
}
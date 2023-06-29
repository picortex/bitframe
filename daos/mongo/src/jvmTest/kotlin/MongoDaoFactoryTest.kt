import bitframe.core.DaoFactory
import bitframe.core.get
import bitframe.server.MongoDaoFactory
import bitframe.server.MongoDaoFactoryConfig
import expect.expect
import kommander.expect
import kotlinx.coroutines.test.runTest
import koncurrent.later.await
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class MongoDaoFactoryTest {

    val config = MongoDaoFactoryConfig(
        host = "127.0.0.1:27017",
        username = "root",
        password = "example",
        database = "test-app"
    )

    private val factory: DaoFactory = MongoDaoFactory(config)

    @Test
    fun can_easily_initiate_a_factory() = runTest {
        val dao = factory.get<Human>()
        dao.create(Human(name = "Jane")).await()
    }

    @Test
    fun gets_the_correct_prefix_of_id_for_a_class() = runTest {
        val dao = factory.get<Human>()
        val pete = dao.create(Human(name = "Peter")).await()
        expect<String>(pete.uid).toBeNonNull()
    }
}
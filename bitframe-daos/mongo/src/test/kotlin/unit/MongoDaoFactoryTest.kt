package unit

import bitframe.daos.DaoFactory
import bitframe.daos.MongoDaoFactory
import bitframe.daos.MongoDaoFactoryConfig
import bitframe.daos.get
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import kotlin.test.Ignore
import kotlin.test.Test

class MongoDaoFactoryTest {

    val config = MongoDaoFactoryConfig(
        host = "127.0.0.1:27017",
        username = "root",
        password = "example",
        database = "test-app"
    )

    private val factory: DaoFactory = MongoDaoFactory(config)

    @Test
    fun can_easily_initiate_a_factory() {
        val dao = factory.get<Human>()
        dao.create(Human(name = "Jane"))
    }

    @Test
    fun gets_the_correct_prefix_of_id_for_a_class() = runTest {
        val dao = factory.get<Human>()
        val pete = dao.create(Human(name = "Peter")).await()
        expect(pete.uid).toBeNonNull()
    }
}